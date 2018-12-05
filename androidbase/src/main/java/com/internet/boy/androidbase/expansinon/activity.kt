package com.internet.boy.androidbase.expansinon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

/**
 *@date: 2018/12/5 - 下午 03:50
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

inline fun <reified T : AppCompatActivity> AppCompatActivity.startActivity(vararg args: Pair<String, Any>) {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*args))
    startActivity(intent)
}