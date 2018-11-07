package com.internet.boy.androidbase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
abstract class BaseLazyFragment : BaseFragment() {

    var isDataLoaded: Boolean = false


    //是否可见
    var isVisble = false
    // 是否初始化完成
    var isPrepared = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(bindLayout(), null, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isPrepared) return

        initView(savedInstanceState)
        doBusiness()
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
            initData(arguments)

            doLazyBusiness()
        }

    }


    companion object {

        private const val TAG = "BaseLazyFragment"
    }
}
