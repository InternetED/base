package com.internet.boy.androidbase.base

import android.os.Bundle

/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
internal interface IBaseView : IBaseClick {

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    val layoutId: Int


    val bundles: Bundle?

    /**
     * 初始化 view
     */
    fun initView(savedInstanceState: Bundle?)


}

