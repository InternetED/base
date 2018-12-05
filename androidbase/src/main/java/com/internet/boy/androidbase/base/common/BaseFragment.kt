package com.internet.boy.androidbase.base.common

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.internet.boy.androidbase.base.IBaseView
import com.internet.boy.androidbase.kutils.logd


/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
abstract class BaseFragment : Fragment(), IBaseView {


    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity


    // 初始化最後點擊時間
    override var lastClickTime: Long = 0

    // 設定間距時間
    override var intervalsTime: Long = 200

    override val bundles: Bundle by lazy { arguments ?: Bundle() }


    override fun onAttach(context: Context) {
        super.onAttach(context)
//        mActivity = context as FragmentActivity
        mContext = context

        mActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)



        savedInstanceState?.let {
            val isSupportHidden = it.getBoolean(STATE_SAVE_IS_HIDDEN)
            val ft = fragmentManager!!.beginTransaction()

            logd("$this , $isSupportHidden", TAG)

            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }
            logd(isSupportHidden.toString(), "isSupportHidden")


            ft.commitAllowingStateLoss()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: ")
        return inflater.inflate(layoutId, null, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            //            bundle = it
        }

        initView(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")

        super.onDestroyView()
    }


    override fun onDestroy() {
        Log.d(TAG, "onDestroy: ")

        view?.let {
            (it.parent as ViewGroup).removeView(it)
        }
        super.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG, "onSaveInstanceState: ")
        super.onSaveInstanceState(outState)
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
    }


    companion object {
        private const val TAG = "BaseFragment"
        private const val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    }
}
