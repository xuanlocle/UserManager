package com.xuanlocle.usermanager.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.xuanlocle.usermanager.R

abstract class BaseActivity : AppCompatActivity() {

    protected fun replaceFragment(fragment: Fragment) {
        ActivityUtils.replaceFragment(supportFragmentManager, fragment, R.id.container)
    }


    fun addFragment(fragment: Fragment) {
        fragment?.let {
            supportFragmentManager.let {
                ActivityUtils.addFragmentToActivityWithTag(
                    supportFragmentManager, fragment, R.id.container, fragment.javaClass.name
                )
            }
        }
    }


}