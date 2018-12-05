package com.internet.boy.androidbase.expansinon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 *@date: 2018/12/5 - 下午 03:55
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, false)
}

