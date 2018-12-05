package com.internet.boy.androidbase.base.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.internet.boy.androidbase.base.IBaseView

/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/


abstract class BaseActivity : AppCompatActivity(), IBaseView {


    // 初始化最後點擊時間
    override var lastClickTime: Long = 0

    // 設定間距時間
    override var intervalsTime: Long = 200


    override val bundles: Bundle by lazy { intent.extras ?: Bundle() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId)

        initView(savedInstanceState)
    }


}
