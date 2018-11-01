package com.internet.boy.androidbase.kutils

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.TypedValue
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.core.content.ContextCompat
import java.io.File
import java.io.IOException
import java.io.InputStream

/**
 * Created by zyyoona7 on 2017/8/26.
 * 资源相关的扩展函数
 *
 */

/*
  ---------- Context ----------
 */
fun Context.loadColor(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)

fun Context.loadDrawable(@DrawableRes id: Int): Drawable? = ContextCompat.getDrawable(this, id)

fun Context.loadRaw(@RawRes id: Int): InputStream? = resources.openRawResource(id)

fun Context.loadRaw(@RawRes id: Int, value: TypedValue): InputStream? = resources.openRawResource(id, value)


fun Context.loadAsset(fileName: String, accessMode: Int = AssetManager.ACCESS_STREAMING): InputStream? {
    return try {
        assets.open(fileName, accessMode)
    } catch (e: IOException) {
        e.printStackTrace()
        null
    }
}

fun Context.loadTypefaceFromAsset(fileName: String): Typeface = Typeface.createFromAsset(assets, fileName)

fun loadTypefaceFromFile(filePath: String): Typeface = Typeface.createFromFile(filePath)

fun loadTypefaceFromFile(file: File): Typeface = Typeface.createFromFile(file)


/*
  ---------- View ----------
 */
fun View.loadColor(@ColorRes id: Int): Int = context.loadColor(id)

fun View.loadDrawable(@ColorRes id: Int): Drawable? = context.loadDrawable(id)

fun View.loadRaw(@RawRes id: Int): InputStream? = context.loadRaw(id)

fun View.loadRaw(@RawRes id: Int, value: TypedValue): InputStream? = context.loadRaw(id, value)

fun View.loadAsset(fileName: String, accessMode: Int = AssetManager.ACCESS_STREAMING): InputStream? = context.loadAsset(fileName, accessMode)

fun View.loadTypefaceFromAsset(fileName: String): Typeface = context.loadTypefaceFromAsset(fileName)
