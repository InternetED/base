package com.internet.boy.androidbase.base.common

import android.os.Bundle
import android.view.View

/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
abstract class BaseLazyFragment : BaseFragment() {

    var isDataLoaded: Boolean = false

    //是否可见
    var isVisble: Boolean = false
    // 是否初始化完成
    var isPrepared: Boolean = false


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isPrepared) return

        initView(savedInstanceState)
        isPrepared = true

    }


    override fun onDestroyView() {
        isVisble = false
        isPrepared = false
        isDataLoaded = false


        super.onDestroyView()
    }


    abstract fun doLazyBusiness()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        if (isVisibleToUser && !isDataLoaded) {

            doLazyBusiness()
        }

    }

}
