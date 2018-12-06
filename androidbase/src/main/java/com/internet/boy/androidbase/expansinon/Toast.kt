package com.internet.boy.androidbase.expansinon

import android.content.Context
import android.widget.Toast


/**
 *@date: 2018/12/5 - 下午 03:51
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

private var mToast: Toast? = null

/**
 * 為了顯示最後一次 Toast
 * */
fun Context.toast(text: String): Toast {
    mToast?.cancel()
    return Toast.makeText(this, text, Toast.LENGTH_SHORT).also {
        mToast = it
        it.show()
    }
}