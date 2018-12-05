package com.internet.boy.androidbase.expansinon

import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 *@date: 2018/12/5 - 下午 04:18
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/

val options by lazy {

    var uiOptions = (
            //穩定布局，不隨著系統欄顯示或隱藏而變化
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or

                    //拓展布局到導航欄後面
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    //隱藏導航欄
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or

                    //拓展佈局到狀態欄後面
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    //隱藏狀態欄
                    View.SYSTEM_UI_FLAG_FULLSCREEN
            )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        uiOptions = uiOptions or

                //沉浸模式，用戶可以交互的介面
                View.SYSTEM_UI_FLAG_IMMERSIVE
    }
    uiOptions
}

//關閉全螢幕的超級沉浸模式
fun AppCompatActivity.closeFullScreen() {
    window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

}

//打開全螢幕的超級沉浸模式
fun AppCompatActivity.openFullScreen() {

    window.decorView.systemUiVisibility = options

}