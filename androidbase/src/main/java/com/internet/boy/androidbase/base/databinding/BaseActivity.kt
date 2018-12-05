package com.internet.boy.androidbase.base.databinding

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.internet.boy.androidbase.base.IBaseView


abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity(), IBaseView {

    protected lateinit var binding: VB


    // 初始化最後點擊時間
    override var lastClickTime: Long = 0

    // 設定間距時間
    override var intervalsTime: Long = 200

    override val bundles: Bundle by lazy { intent.extras ?: Bundle() }


    abstract fun initView()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBinding()

        initView()
    }

    private fun initBinding() {
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setLifecycleOwner(this)

    }


}