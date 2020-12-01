package com.internet.boy.androidbase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.internet.boy.androidbase.kutils.windowManager

/**
 *@date: 2018/9/8 : 上午 10:29
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

private var mToast: Toast? = null


inline fun <reified T : AppCompatActivity> AppCompatActivity.startActivity(vararg args: Pair<String, Any>) {

    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*args))
    startActivity(intent)
}


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


fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, false)
}

fun Activity.enterImmersive() {
    window.decorView.systemUiVisibility =
        (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
}


fun Activity.exitImmersive() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
}




