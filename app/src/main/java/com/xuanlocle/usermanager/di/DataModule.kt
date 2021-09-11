package com.xuanlocle.usermanager.di

import com.xuanlocle.usermanager.data.source.local.UserDatabase
import com.xuanlocle.usermanager.data.source.local.UserLocalDataSource
import com.xuanlocle.usermanager.data.source.local.UserLocalDataSourceImpl
import com.xuanlocle.usermanager.data.source.remote.UserRemoteDataSource
import com.xuanlocle.usermanager.data.source.remote.UserRemoteDataSourceImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

val dataModule = Kodein.Module("modelModule") {

    bind() from singleton { UserDatabase(instance()) }

    bind<UserLocalDataSource>() with provider { UserLocalDataSourceImpl(instance()) }
    bind<UserRemoteDataSource>() with provider { UserRemoteDataSourceImpl(instance()) }

}