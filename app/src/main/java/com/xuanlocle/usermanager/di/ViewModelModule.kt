package com.xuanlocle.usermanager.di

import com.xuanlocle.usermanager.ui.user_list.UserListViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val viewModelModule = Kodein.Module("viewmodelModule") {

    bind() from provider { UserListViewModelFactory(instance()) }
}