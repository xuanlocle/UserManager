package com.xuanlocle.usermanager.util

import android.content.Context
import android.content.SharedPreferences

class UsersPreference(context: Context) {

    companion object {
        const val SKEY_LAST_UPDATED_AT = "SKEY_LAST_UPDATED_AT"
    }

    private var sp: SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    private fun clear(key: String) {
        sp.edit().remove(key).apply()
    }

    fun setLastUpdateAt(lastUpdatedAt: Long) {
        sp.edit().putLong(SKEY_LAST_UPDATED_AT, lastUpdatedAt).apply()
    }

    fun getLastUpdatedAt(): Long {
        return sp.getLong(SKEY_LAST_UPDATED_AT, 0L)
    }

    fun clear(keys: List<String>) {
        keys.forEach {
            clear(it)
        }
    }

}