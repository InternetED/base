package com.internet.boy.androidbase.common

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.internet.boy.androidbase.kutils.logd

class CustomGridLayoutManager(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {


    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (indexOutOf: IndexOutOfBoundsException) {
            logd("出現了IndexOutOfBoundsException", "GridLayoutManager")
        } catch (e: Exception) {
            logd("出現了Exception", "GridLayoutManager")
            // 為了解決
            //java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionViewHolder{2064e5c6 position=2 id=-1, oldPos=2, pLpos:-1 scrap [attachedScrap] tmpDetached no parent}
        }

    }

}

