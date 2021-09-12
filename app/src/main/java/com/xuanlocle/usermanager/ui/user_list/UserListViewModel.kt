package com.xuanlocle.usermanager.ui.user_list

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuanlocle.usermanager.data.model.UserModel
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult
import com.xuanlocle.usermanager.data.source.repository.UserRepository
import com.xuanlocle.usermanager.ui.base.BaseViewModel
import com.xuanlocle.usermanager.usersPreference
import com.xuanlocle.usermanager.util.Constants
import com.xuanlocle.usermanager.util.datetime.DateTimeHelper
import com.xuanlocle.usermanager.widget.MutableLiveDataSingle
import kotlinx.coroutines.launch

class UserListViewModel(private val repository: UserRepository) : BaseViewModel() {

    private var reposLiveData = MutableLiveData<List<UserModel>>()
    val repos: LiveData<List<UserModel>>
        get() = reposLiveData

    private var reposMoreLiveData = MutableLiveDataSingle<List<UserModel>>()
    val reposMore: LiveData<List<UserModel>>
        get() = reposMoreLiveData

    val isLoading: LiveData<Boolean> get() = showLoading

    private val showLoadingMore = MutableLiveDataSingle<Boolean>()
    val isLoadingMore: LiveData<Boolean> get() = showLoadingMore

    /**
     * This field init = 0 when first launch. Then update with last user id of list.
     */
    private var mLastRowUserId = 0

    fun getUserLists() {
        showLoading.value = true
        uiScope.launch {
            if (usersPreference.getLastUpdatedAt() > 0) {
                if (DateTimeHelper.isMoreOneDay(usersPreference.getLastUpdatedAt())) {
                    repository.removeOldData() //cache for 1 day
                }
            }

            when (val result = repository.fetchUsers(mLastRowUserId)) {
                is BaseResult.Success -> {
                    showLoading.value = false
                    if (result.data != null) {
                        val reposModel = result.data
                        saveLastRowUserId(reposModel.last().id)
                        reposLiveData.value = reposModel
                    }
                }
                is BaseResult.Error -> {
                    showLoading.value = false
                }

                is BaseResult.Loading -> {
                    showLoading.value = true
                }
            }
        }
    }

    fun getMoreUserLists() {
        showLoadingMore.value = true
        uiScope.launch {
            when (val result = repository.fetchUsers(mLastRowUserId)) {
                is BaseResult.Success -> {
                    showLoading.value = false
                    if (result.data != null) {
                        showLoadingMore.value = false
                        val reposModel = result.data
                        saveLastRowUserId(reposModel.last().id)
                        reposMoreLiveData.value = reposModel

                    }
                }
                is BaseResult.Error -> {
                    showLoadingMore.value = false
                }

                is BaseResult.Loading -> {
                    showLoadingMore.value = true
                }
            }
        }
    }


    private fun saveLastRowUserId(lastRowUserId: Int) {
        this.mLastRowUserId = lastRowUserId
    }

    override fun saveState(outState: Bundle) {
        outState.putInt(Constants.BundleKey.LAST_ROW_USER_ID, mLastRowUserId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(Constants.BundleKey.LAST_ROW_USER_ID))
            mLastRowUserId = savedInstanceState.getInt(Constants.BundleKey.LAST_ROW_USER_ID)
    }

    fun reloadUserLists() {
        mLastRowUserId = 0
        uiScope.launch {
            repository.removeOldData()

            when (val result = repository.fetchUsers(mLastRowUserId)) {
                is BaseResult.Success -> {
                    showLoading.value = false
                    if (result.data != null) {
                        val reposModel = result.data
                        saveLastRowUserId(reposModel.last().id)
                        reposLiveData.value = reposModel
                    }
                }
                is BaseResult.Error -> {
                    showLoading.value = false
                }

                is BaseResult.Loading -> {
                    showLoading.value = true
                }
            }
        }

    }
}
