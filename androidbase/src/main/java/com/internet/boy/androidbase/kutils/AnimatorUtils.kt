package com.internet.boy.androidbase.kutils

import android.animation.AnimatorSet
import android.view.animation.DecelerateInterpolator


/**
 * 將傳進來的動畫創建
 * 時間 : 0.3秒
 * 減速插植器
 * */
fun createAnimator(block: AnimatorSet.() -> Unit): AnimatorSet {
    //整合動畫
    val animatorSet = AnimatorSet()
    animatorSet.duration = 300 //動畫時間
    animatorSet.interpolator = DecelerateInterpolator()// 减速插值器
    animatorSet.block()
    return animatorSet

}