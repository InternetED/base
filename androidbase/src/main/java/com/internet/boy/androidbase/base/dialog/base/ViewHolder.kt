package com.internet.boy.androidbase.base.dialog.base

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.annotation.*

/**
 * Date 2018/6/29
 *
 * @author limuyang
 */
@Suppress("UNCHECKED_CAST")
class ViewHolder private constructor(private val convertView: View) {
    private val views: SparseArray<View> = SparseArray()

    fun <T : View> getView(viewId: Int): T {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = convertView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as T
    }

    companion object {

        fun create(view: View): ViewHolder {
            return ViewHolder(view)
        }
    }

    fun setText(@IdRes viewId: Int, value: CharSequence): ViewHolder {
        val view = getView<TextView>(viewId)
        view.text = value
        return this
    }

    fun setText(@IdRes viewId: Int, @StringRes strId: Int): ViewHolder {
        val view = getView<TextView>(viewId)
        view.setText(strId)
        return this
    }


    fun setTextColor(@IdRes viewId: Int, @ColorInt colorId: Int): ViewHolder {
        val textView = getView<TextView>(viewId)
        textView.setTextColor(colorId)
        return this
    }


    fun setOnClickListener(@IdRes viewId: Int, @Nullable clickListener: View.OnClickListener?): ViewHolder {
        val view = getView<View>(viewId)
        view.setOnClickListener(clickListener)
        return this
    }


    fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes resId: Int): ViewHolder {
        val view = getView<View>(viewId)
        view.setBackgroundResource(resId)
        return this
    }

    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt colorId: Int): ViewHolder {
        val view = getView<View>(viewId)
        view.setBackgroundColor(colorId)
        return this
    }

}
