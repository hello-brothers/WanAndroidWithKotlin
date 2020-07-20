package com.lh.wanandroid.widget.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView

/**
 *@author: lh
 *CreateDate: 2020/7/20
 */
class SampleTitleBehavior<V: View>(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attrs) {

    private var deltaY: Float = 0f
    override fun layoutDependsOn(parent: CoordinatorLayout, child: V, dependency: View) = dependency is RecyclerView

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: V,
        dependency: View
    ): Boolean {
        if (deltaY.equals(0)){
            deltaY = dependency.y - child.height
        }
        var dy = dependency.y - child.height
        dy = if (dy < 0) 0F else dy
        val y = -(dy/deltaY) * child.height
        child.translationY = y

        return true
    }

}