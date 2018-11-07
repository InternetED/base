package com.internet.boy.androidbase.kutils

import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.DimenRes


/**
 * Created by zyyoona7 on 2017/8/24.
 *
 * 尺寸、尺寸轉換 擴展函數
 * dimension extensions
 */
/*
  ---------- Context ----------
 */

/**
 * screen width in pixels
 */
val Context.screenWidth
    get() = resources.displayMetrics.widthPixels

/**
 * screen height in pixels
 */
val Context.screenHeight
    get() = resources.displayMetrics.heightPixels

/**
 * statusBar height  in pixels
 * */
val Context.statusBarHeight: Int
    get() {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelOffset(resourceId)
    }
/**
 * navigationBar height in pixels
 * */
val Context.navigationBarHeight: Int
    get() {
        var navigationBarHeight = 0
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0 && hasNavigationBar) {

            navigationBarHeight = resources.getDimensionPixelSize(resourceId)
        }
        return navigationBarHeight
    }
/**
 * has navigationBar in bool
 * */
val Context.hasNavigationBar: Boolean
    get() {
        val display = windowManager?.defaultDisplay ?: return true

        val realDisplayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            display.getRealMetrics(realDisplayMetrics)
        }
        val realHeight = realDisplayMetrics.heightPixels
        val realWidth = realDisplayMetrics.widthPixels


        val displayMetrics = DisplayMetrics()
        display.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels
        val displayWidth = displayMetrics.widthPixels

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0


    }

/**
 * returns dip(dp) dimension value in pixels
 * @param value dp
 */
fun Context.dip2px(value: Int): Int = (value * resources.displayMetrics.density).toInt()

fun Context.dip2px(value: Float): Int = (value * resources.displayMetrics.density).toInt()

/**
 * return sp dimension value in pixels
 * @param value sp
 */
fun Context.sp2px(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt()

fun Context.sp2px(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt()

/**
 * converts [px] value into dip or sp
 * @param px
 */
fun Context.px2dip(px: Int): Float = px.toFloat() / resources.displayMetrics.density

fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

/**
 * return dimen resource value in pixels
 * @param resource dimen resource
 */
fun Context.dimen2px(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

