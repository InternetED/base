package com.internet.boy.androidbase.utils

import java.util.*


fun Long.timeToString(): String {
//            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")


    val ns: Long = 1000
    val nm = ns * 60
    val nh = nm * 60
    val nd = nh * 24
    val nM = nd * 30
    val ny = nd * 365

    val nowTime = Date().time

    val differenceTime = nowTime - this
    val year = differenceTime / ny
    if (year != 0L) {
        return year.toString() + "年前"
    }

    val month = differenceTime / nM
    if (month != 0L) {
        return month.toString() + "月前"
    }
    // 計算差多少天
    val day = differenceTime / nd
    if (day != 0L) {
        return day.toString() + "天前"
    }
    // 計算差多少小時
    val hour = differenceTime % nd / nh
    if (hour != 0L) {
        return hour.toString() + "小時前"
    }
    // 計算差多少分鐘
    val min = differenceTime % nd % nh / nm
    return if (min != 0L) {
        min.toString() + "分前"
    } else "剛剛"
    //            // 計算差多少秒
    //            long sec = calTT % nd % nh % nm / ns;

}