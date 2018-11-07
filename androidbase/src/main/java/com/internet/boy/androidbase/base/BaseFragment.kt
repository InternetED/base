package com.internet.boy.androidbase.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.internet.boy.androidbase.curFragment
import com.internet.boy.androidbase.kutils.logd


/**
 *@date: 2018/9/8 : 下午 03:46
 *@author: Ed
 *@email: salahayo3192@gmail.com
 **/
abstract class BaseFragment : Fragment(), IBaseView {


    lateinit var mContext: Context
    lateinit var mActivity: AppCompatActivity


    /**
     * 上次點擊時間
     */
    private var lastClick: Long = 0

    /**
     * 判断是否快速點擊
     *
     * @return `true`: 是   `false`: 否
     */
    protected val isFastClick: Boolean
        get() {
            val now = System.currentTimeMillis()
            if (now - lastClick >= 200) {
                lastClick = now
                return false
            }
            return true
        }


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
        return inflater.inflate(bindLayout(), null, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: ")
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        initData(bundle)
        initView(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d(TAG, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
        doBusiness()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView: ")

        super.onDestroyView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            curFragment = this
        }
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

    override fun onClick(view: View) {
        if (!isFastClick) onWidgetClick(view)
    }


    companion object {
        private const val TAG = "BaseFragment"
        private const val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    }
}
