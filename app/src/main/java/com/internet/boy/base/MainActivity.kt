package com.internet.boy.base

import android.os.Bundle
import com.internet.boy.androidbase.base.common.BaseActivity
import com.internet.boy.androidbase.expansinon.closeFullScreen
import com.internet.boy.androidbase.expansinon.openFullScreen
import com.internet.boy.androidbase.expansinon.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_main



    override fun initView(savedInstanceState: Bundle?) {



        btnBegin.setOnClickListener {

            toast("$isFastClick")
            closeFullScreen()
        }

        btnEnd.setOnClickListener {
            openFullScreen()
        }
    }


}
