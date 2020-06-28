package com.lh.wanandroid.utils

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import com.lh.wanandroid.R

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
object StatusBarUtil {

    private const val FAKE_STATUS_BAR_VIEW_ID = R.id.statusbarutil_fake_status_bar_view
    private const val FAKE_TRANSLUCENT_VIEW_ID = R.id.statusbarutil_translucent_view

    fun setColor(
        activity: Activity,
        @ColorInt color: Int,
        @IntRange(from = 0, to = 255) statusBarAlpha: Int
    ){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            activity.window.statusBarColor = calculateStatusColor(color, statusBarAlpha)
        }else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            val decorView = activity.window.decorView as ViewGroup
            val fakeStatusBarView = decorView.findViewById<View>(FAKE_STATUS_BAR_VIEW_ID)
            if (fakeStatusBarView != null){
                if (fakeStatusBarView!!.visibility === View.GONE){
                    fakeStatusBarView!!.visibility = View.VISIBLE
                }
                fakeStatusBarView!!.setBackgroundColor(calculateStatusColor(color, statusBarAlpha))
            }else{
                decorView.addView(createStatusBarView(activity, color, statusBarAlpha))
            }
            setRootView(activity)
        }
    }

    /** 计算状态栏颜色 **/
    private fun calculateStatusColor(@ColorInt color: Int, @IntRange(from = 0, to = 255) alpha: Int): Int{
        if (alpha == 0){
            return color
        }
        val a = 1 - alpha / 255f
        var red = color shr 16 and 0xff
        var green = color shr 8 and 0xff
        var blue = color and 0xff
        red = (red * a + 0.5).toInt()
        green = (green * a + 0.5).toInt()
        blue = (blue * a + 0.5).toInt()
        return 0xff shl 24 or (red shl 16) or (green shl 8) or blue

    }

    private fun createStatusBarView(activity: Activity, @ColorInt color: Int): View{
        return createStatusBarView(activity, color, 0)
    }

    private fun createStatusBarView(activity: Activity, @ColorInt color: Int, @IntRange(from = 0, to = 255) alpha: Int): View{
        val statusBarView = View(activity)
        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
        statusBarView.layoutParams = params
        statusBarView.setBackgroundResource(calculateStatusColor(color, alpha))
        statusBarView.id = FAKE_STATUS_BAR_VIEW_ID
        return statusBarView

    }

    private fun getStatusBarHeight(context: Context): Int{
        val  resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        return context.resources.getDimensionPixelSize(resourceId)
    }

    private fun setRootView(activity: Activity){
        val parent = activity.findViewById<View>(android.R.id.content) as ViewGroup
        var i = 0
        val count = parent.childCount
        while (i < count){
            val childView = parent.getChildAt(i)
            if (childView is ViewGroup){
                childView.fitsSystemWindows = true
                childView.clipToPadding = true
            }
        }
    }
}