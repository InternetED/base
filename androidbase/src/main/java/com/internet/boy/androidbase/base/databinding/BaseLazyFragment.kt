package com.internet.boy.androidbase.base.databinding

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding

abstract class BaseLazyFragment<VB : ViewDataBinding> : BaseFragment<VB>() {

    // 資料是否加載完成
    var isDataLoaded: Boolean = false

    // 是否初始化完成
    var isPrepared = false

    abstract fun doLazyBusiness()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && !isDataLoaded) {

            doLazyBusiness()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (isPrepared) return

        super.onViewCreated(view, savedInstanceState)
        isPrepared = true
    }


}