package com.internet.boy.androidbase.base

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.view.WindowManager

/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/


abstract class BaseActivity : AppCompatActivity(), IBaseView {

    /**
     * 上次點擊時間
     */
    private var lastClick: Long = 0

    /**
     * 判断是否快速點擊
     *
     * @return `true`: 是   `false`: 否
     */
    private val isFastClick: Boolean
        get() {
            val now = System.currentTimeMillis()
            if (now - lastClick >= 200) {
                lastClick = now
                return false
            }
            return true
        }


    protected var uiOptions = -1// 收縮狀態列及導航欄 參數  (-1 為 版本16以下)
    private val mDecorView: View by lazy { window.decorView }// 收縮狀態列及導航欄 View

    override fun onStart() {
        super.onStart()

        if (Build.VERSION.SDK_INT >= 16) {
            uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE //穩定布局，不隨著系統欄顯示或隱藏而變化

                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION//拓展布局到導航欄後面
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION//隱藏導航欄

                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN//拓展佈局到狀態欄後面
                    or View.SYSTEM_UI_FLAG_FULLSCREEN//隱藏狀態欄
                    )
        }
        if (Build.VERSION.SDK_INT >= 19) {
            uiOptions = uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE//沉浸模式，用戶可以交互的介面
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        initData(bundle)
        setContentView(bindLayout())
        initView(savedInstanceState)
        doBusiness()
    }

    override fun onClick(view: View) {
        if (!isFastClick) onWidgetClick(view)
    }

    // 狀態列還在的全螢幕模式
    protected fun openFullScreen() {
        // uiOptions 若不為 -1 ，則此 Android 裝置版本 > 16
        if (uiOptions != -1) {

            mDecorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)


        }

    }

    //全部全螢幕的超級沈浸模式
    protected fun closeFullScreen() {
        // uiOptions 若不為 -1 ，則此 Android 裝置版本 > 16
        if (uiOptions != -1) {
            mDecorView.systemUiVisibility = uiOptions

        } else {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }
}
