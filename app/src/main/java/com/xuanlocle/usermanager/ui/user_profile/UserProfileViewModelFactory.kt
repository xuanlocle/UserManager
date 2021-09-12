package com.xuanlocle.usermanager.ui.user_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xuanlocle.usermanager.data.source.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class UserProfileViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UserProfileViewModel(repository) as T
    }

}
