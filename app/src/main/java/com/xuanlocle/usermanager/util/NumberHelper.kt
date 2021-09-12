package com.xuanlocle.usermanager.util

import java.text.DecimalFormat
import kotlin.math.floor
import kotlin.math.log10
import kotlin.math.pow

object NumberHelper {

    private val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')

    fun calcNumberThousand(numValue: Long): String {
        val value = floor(log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / 10.0.pow((base * 3).toDouble())
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }
}