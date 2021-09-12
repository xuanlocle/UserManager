package com.xuanlocle.usermanager.ui.user_profile

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xuanlocle.usermanager.data.model.UserProfileModel
import com.xuanlocle.usermanager.data.source.remote.response.BaseResult
import com.xuanlocle.usermanager.data.source.repository.UserRepository
import com.xuanlocle.usermanager.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class UserProfileViewModel(private val repository: UserRepository) : BaseViewModel() {

    private var profile = MutableLiveData<UserProfileModel>()
    val profileLiveData: LiveData<UserProfileModel>
        get() = profile


    fun getProfile(userLoginId: String) {
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

    override fun saveState(outState: Bundle) {

    }

    override fun restoreState(savedInstanceState: Bundle) {
    }

}
