package com.internet.boy.androidbase.base.dialog


import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import androidx.fragment.app.FragmentManager
import com.internet.boy.androidbase.base.dialog.base.BaseLDialog
import com.internet.boy.androidbase.base.dialog.base.ViewHandlerListener

/**
 *
 * Date 2018/6/27
 * @author limuyang
 */
class LDialog : BaseLDialog<LDialog>() {

    override fun layoutRes(): Int = 0

    override fun layoutView(): View? = null

    override fun viewHandler(): ViewHandlerListener? {
        return null
    }

    fun setLayoutRes(@LayoutRes layoutRes: Int): LDialog {
        baseParams.layoutRes = layoutRes
        return this
    }

    fun setLayoutView(view: View): LDialog {
        baseParams.view = view
        return this
    }

    fun setViewHandlerListener(viewHandlerListener: ViewHandlerListener): LDialog {
        this@LDialog.viewHandlerListener = viewHandlerListener
        return this
    }

    companion object {
        fun init(fragmentManager: FragmentManager): LDialog {
            val dialog = LDialog()
            dialog.setFragmentManager(fragmentManager)
            return dialog
        }
    }
}


/**
 * Dialog 基類
 * */
fun AppCompatActivity.createDialog(block: LDialog.() -> LDialog): LDialog {
    return LDialog.init(supportFragmentManager)
            .setGravity(Gravity.CENTER)
            .setWidthScale(0.8f)
            .setVerticalMargin(0.015f)
            .block()

}