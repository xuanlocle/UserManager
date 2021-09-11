package com.xuanlocle.usermanager.ui.landing

import android.os.Bundle
import com.xuanlocle.usermanager.R
import com.xuanlocle.usermanager.ui.base.BaseActivity
import com.xuanlocle.usermanager.ui.user_list.UserListFragment


class LandingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        savedInstanceState ?: addFragment(UserListFragment.newInstance())
    }
}