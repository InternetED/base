package com.internet.boy.androidbase.base.databinding

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.internet.boy.androidbase.base.IBaseView


abstract class BaseFragment<VB : ViewDataBinding> : Fragment(), IBaseView {

    protected lateinit var binding: VB

    private lateinit var mRootView: View

    protected lateinit var mContext: Context
    protected lateinit var mActivity: AppCompatActivity


    // 初始化最後點擊時間
    override var lastClickTime: Long = 0

    // 設定間距時間
    override var intervalsTime: Long = 200


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context

        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        savedInstanceState?.let {

            val isSupportHidden = it.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft = fragmentManager!!.beginTransaction()


            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }


            ft.commitAllowingStateLoss()

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        mRootView = LayoutInflater.from(context).inflate(layoutId, container, false)


        return mRootView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initBinding(view)

        initView(savedInstanceState)

    }

    private fun initBinding(rootView: View) {
        binding = DataBindingUtil.bind(rootView)!!
        binding.setLifecycleOwner(this)
    }


    override fun onDestroy() {

        view?.let {
            (it.parent as ViewGroup).removeView(it)
        }

        super.onDestroy()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }


    companion object {
        private const val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    }
}