package com.xuanlocle.usermanager.ui.user_profile

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuanlocle.usermanager.data.model.UserProfileModel
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult
import com.xuanlocle.usermanager.data.source.repository.UserRepository
import com.xuanlocle.usermanager.ui.base.BaseViewModel
import com.xuanlocle.usermanager.util.Constants
import kotlinx.coroutines.launch

class UserProfileViewModel(private val repository: UserRepository) : BaseViewModel() {

    private var profile = MutableLiveData<UserProfileModel>()
    val profileLiveData: LiveData<UserProfileModel>
        get() = profile

    val isLoading: LiveData<Boolean> get() = showLoading

    private lateinit var profileId: String

    fun getProfile(userLoginId: String) {
        profileId = userLoginId
        showLoading.value = true
        uiScope.launch {
            when (val result = repository.fetchUserProfileById(userLoginId)) {
                is BaseResult.Success -> {
                    showLoading.value = false
                    if (result.data != null) {
                        val userData = result.data
                        profile.value = userData
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

    fun reloadProfile() {
        showLoading.value = true
        uiScope.launch {
            when (val result = repository.fetchUpdateUserProfileById(profileId)) {
                is BaseResult.Success -> {
                    showLoading.value = false
                    if (result.data != null) {
                        val userData = result.data
                        profile.value = userData
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

    override fun saveState(outState: Bundle) {
        outState.putString(Constants.BundleKey.USER_ID, profileId)
    }

    override fun restoreState(savedInstanceState: Bundle) {
        if (savedInstanceState.containsKey(Constants.BundleKey.USER_ID)) {
            profileId = savedInstanceState.getString(Constants.BundleKey.USER_ID) ?: ""
        }
    }

}
