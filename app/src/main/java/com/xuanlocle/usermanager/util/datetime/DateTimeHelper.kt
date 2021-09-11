package com.xuanlocle.usermanager.util.datetime

import java.util.*

object DateTimeHelper {

    private const val ONE_SECOND: Long = 1000
    private const val ONE_MINUTE: Long = ONE_SECOND * 60
    private const val ONE_HOUR: Long = ONE_MINUTE * 60
    private const val ONE_DAY: Long = ONE_HOUR * 24

    fun getCurrentDateTime(): Long {
        return Date().time
    }

    fun isMoreOneDay(dateTime: Long): Boolean {
        return (Date().time - dateTime) > ONE_DAY
    }

}