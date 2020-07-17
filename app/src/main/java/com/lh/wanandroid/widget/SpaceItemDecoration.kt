package com.lh.wanandroid.widget

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 *@author: lh
 *CreateDate: 2020/7/17
 */
class SpaceItemDecoration: RecyclerView.ItemDecoration() {


    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (getOrientation(parent.layoutManager!!) == RecyclerView.VERTICAL){

            outRect.bottom  = 1
        }else outRect.right = 1
    }

    private fun getOrientation(layoutManager: RecyclerView.LayoutManager): Int{
        var orientation = OrientationHelper.HORIZONTAL
        when(layoutManager){
            is LinearLayoutManager -> orientation = layoutManager.orientation
            is StaggeredGridLayoutManager -> orientation = layoutManager.orientation
        }
        return orientation
    }
}