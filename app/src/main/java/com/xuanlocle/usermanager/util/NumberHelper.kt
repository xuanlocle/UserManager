package com.xuanlocle.usermanager.util

object NumberHelper {

    private const val THOUSAND: Int = 1000
    private const val MILLION: Int = 1000000
    private const val BILLION: Int = 1000000000

    fun calcNumberThousand(x: Int?): String {

        if (x == null || x == 0)
            return "0"

        return when {
            x < THOUSAND -> x.toString()
            x < MILLION -> "${x.times(100).div(THOUSAND).times(0.01)}K"
            x < BILLION -> "${x.times(100).div(MILLION).times(0.01)}M"
            else -> "${x.times(100).div(BILLION).times(0.01)}B"
        }
    }
}