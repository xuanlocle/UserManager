package com.xuanlocle.usermanager

import androidx.multidex.MultiDexApplication
import com.xuanlocle.usermanager.di.appModule
import com.xuanlocle.usermanager.di.dataModule
import com.xuanlocle.usermanager.util.UsersPreference
import com.xuanlocle.usermanager.di.repositoryModule
import com.xuanlocle.usermanager.di.viewModelModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

val usersPreference: UsersPreference by lazy { UserManagerApplication.prefs }

class UserManagerApplication : MultiDexApplication(), KodeinAware {

    companion object {
        lateinit var instance: UserManagerApplication
        lateinit var prefs: UsersPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = UsersPreference(applicationContext)
        instance = this
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@UserManagerApplication))
        importAll(dataModule, appModule, repositoryModule, viewModelModule)
    }
}