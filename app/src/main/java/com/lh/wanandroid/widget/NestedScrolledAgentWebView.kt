package com.lh.wanandroid.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.core.view.MotionEventCompat
import androidx.core.view.NestedScrollingChild
import androidx.core.view.NestedScrollingChildHelper
import androidx.core.view.ViewCompat
import com.just.agentweb.AgentWebView
import kotlin.math.max

/**
 * Author: lh
 * Description: 结合CoordinatorLayout可以与Toolbar联动的webview
 * Data: 2020/10/11
 */
class NestedScrolledAgentWebView(context: Context, attrs: AttributeSet? = null) :
    AgentWebView(context, attrs), NestedScrollingChild {

    private val mScrollConsumed: IntArray = IntArray(2)
    private val mScrollOffset: IntArray = IntArray(2)
    private val mChildHelper by lazy {
        NestedScrollingChildHelper(this)
    }

    init {
        isNestedScrollingEnabled = true
    }

    private var mNestedYOffset = 0
    private var mLastMotionY = 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var result = false

        val trackEvent = MotionEvent.obtain(event)
        val action = event.actionMasked

        if (action == MotionEvent.ACTION_DOWN){
            mNestedYOffset = 0;
        }

        val y = event.y.toInt()

        event.offsetLocation(0f, mNestedYOffset.toFloat())

        when(action){
            MotionEvent.ACTION_DOWN ->{
                mLastMotionY = y
                result = super.onTouchEvent(event)
            }

            MotionEvent.ACTION_MOVE ->{
                var deltaY = mLastMotionY - y

                if (startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL) &&
                        dispatchNestedPreScroll(0, deltaY, mScrollConsumed, mScrollOffset)){
                    deltaY -= mScrollConsumed[1]
                    trackEvent.offsetLocation(0f, mScrollOffset[1].toFloat())
                    mNestedYOffset += mScrollOffset[1]
                }

                mLastMotionY = y - mScrollOffset[1]

                val oldY = scrollY
                val newScrollY = max(0, oldY + deltaY)
                val dyConsumed = newScrollY - oldY
                val dyUnconsumed = deltaY - dyConsumed

                if (dispatchNestedScroll(0, dyConsumed, 0, dyUnconsumed, mScrollOffset)){
                    mLastMotionY -= mScrollOffset[1]
                    trackEvent.offsetLocation(0f, mScrollOffset[1].toFloat())
                    mNestedYOffset += mScrollOffset[1]
                }

                result = super.onTouchEvent(trackEvent)
                trackEvent.recycle()
            }

            MotionEvent.ACTION_POINTER_DOWN,
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL ->{
                stopNestedScroll()
                result = super.onTouchEvent(event)
            }
        }

        return result
    }

    /** 惯性滑动之后相关操作 **/
    override fun dispatchNestedFling(
        velocityX: Float,
        velocityY: Float,
        consumed: Boolean
    ): Boolean {
        return mChildHelper.dispatchNestedFling(velocityX, velocityY, consumed)
    }

    /** 惯性滑动之前相关操作 **/
    override fun dispatchNestedPreFling(velocityX: Float, velocityY: Float): Boolean {
        return mChildHelper.dispatchNestedPreFling(velocityX, velocityY)
    }

    /** 子view滚动前调用，询问父view是否要在子view之前进行滚动 **/
    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?
    ): Boolean {
        return mChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }

    /** 子view滚动后调用，询问父view是否还要进行余下的滚动 **/
    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?
    ): Boolean {
        return mChildHelper.dispatchNestedScroll(
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            offsetInWindow
        )
    }

    /** 此视图是否具有嵌套滚动的父级 **/
    override fun hasNestedScrollingParent(): Boolean {
        return mChildHelper.hasNestedScrollingParent()
    }

    /** 此视图如果启动用嵌套滚动则返回true **/
    override fun isNestedScrollingEnabled(): Boolean {
        return mChildHelper.isNestedScrollingEnabled
    }

    /** 启动或者禁用视图的嵌套滚动 **/
    override fun setNestedScrollingEnabled(enabled: Boolean) {
        mChildHelper.isNestedScrollingEnabled = enabled
    }

    /** 根据给定轴开始嵌套滚动操作 **/
    override fun startNestedScroll(axes: Int): Boolean {
        return mChildHelper.startNestedScroll(axes)
    }

    /** 停止正在进行的嵌套滚动 **/
    override fun stopNestedScroll() {
        mChildHelper.stopNestedScroll()
    }
}