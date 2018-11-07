package com.internet.boy.androidbase.common

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CustomGridLayoutManager(context: Context, spanCount: Int) : GridLayoutManager(context, spanCount) {


    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (indexOutOf: IndexOutOfBoundsException) {
            Log.d("$indexOutOf", "GridLayoutManager出現錯誤")

        } catch (e: Exception) {
            Log.d("$e", "GridLayoutManager出現錯誤")
            // 為了解決
            //java.lang.IndexOutOfBoundsException: Inconsistency detected. Invalid view holder adapter positionViewHolder{2064e5c6 position=2 id=-1, oldPos=2, pLpos:-1 scrap [attachedScrap] tmpDetached no parent}
        }

    }

}

