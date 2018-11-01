package com.internet.boy.androidbase.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import android.view.View

/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
internal interface IBaseView : View.OnClickListener {

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    fun initData(bundle: Bundle?)

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    @LayoutRes
    @NonNull
    fun bindLayout(): Int

    /**
     * 初始化 view
     */
    fun initView(savedInstanceState: Bundle?)

    /**
     * 业务操作
     */
    fun doBusiness()

    /**
     * 视图点击事件
     *
     * @param v 视图
     */
    fun onWidgetClick(v: View)
}
