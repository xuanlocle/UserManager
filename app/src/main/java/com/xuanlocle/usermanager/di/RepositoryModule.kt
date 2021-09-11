package com.xuanlocle.usermanager.di

import com.xuanlocle.usermanager.data.source.repository.UserRepository
import com.xuanlocle.usermanager.data.source.repository.UserRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider

val repositoryModule = Kodein.Module("repositoryModule") {

    bind<UserRepository>() with provider { UserRepositoryImpl(instance(), instance()) }

}