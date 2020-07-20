package com.lh.wanandroid.widget.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.view.ViewPropertyAnimatorListener
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator

/**
 *@author: lh
 *CreateDate: 2020/7/20
 */
class ScaleBehavior<V: View>(context: Context?, attrs: AttributeSet?) :
    CoordinatorLayout.Behavior<V>(context, attrs) {


    private val mLinearOutSlowInInterpolator: LinearOutSlowInInterpolator by lazy {
        LinearOutSlowInInterpolator()
    }


    private val mFastOutLinearInInterpolator: FastOutLinearInInterpolator by lazy {
        FastOutLinearInInterpolator()
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    )
            = axes ==ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        super.onNestedScroll(
            coordinatorLayout,
            child,
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )

        if (dyConsumed > 0 && child.visibility == View.VISIBLE && !isAnimatorRunning){
            Log.d("TAG", "向上滚动")
            hide(child)
        }else if (dyConsumed < 0 && child.visibility == View.INVISIBLE && !isAnimatorRunning){
            Log.d("TAG", "向下滚动")
            show(child)
        }
    }


    private var isAnimatorRunning = false
    private fun show(child: View) {
        ViewCompat.animate(child).run {
            scaleX(1f)
            scaleY(1f)
            duration = 600
            interpolator = mLinearOutSlowInInterpolator
            setListener(object : ViewPropertyAnimatorListener{
                override fun onAnimationEnd(view: View?) {
                    isAnimatorRunning = false
                }

                override fun onAnimationCancel(view: View?) {
                    isAnimatorRunning = false
                }

                override fun onAnimationStart(view: View?) {
                    isAnimatorRunning = true
                    child.visibility = View.VISIBLE
                }
            })
        }
    }

    private fun hide(child: View) {
        ViewCompat.animate(child).run {
            scaleY(0f)
            scaleX(0f)
            duration = 600
            interpolator = mFastOutLinearInInterpolator
            setListener(object : ViewPropertyAnimatorListener{
                override fun onAnimationEnd(view: View?) {
                    isAnimatorRunning = false
                }

                override fun onAnimationCancel(view: View?) {
                    isAnimatorRunning = false
                }

                override fun onAnimationStart(view: View?) {
                    isAnimatorRunning = true
                    child.visibility = View.INVISIBLE
                }

            })
        }
    }
}