package com.lh.wanandroid.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.lh.wanandroid.R
import com.lh.wanandroid.utils.ColorUtil
import com.lh.wanandroid.utils.SettingUtil

/**
 * Author: lh
 * Description:对网页ViewGroup添加黑色半透明（黑色模式下）
 * Data: 2020/10/12
 */
class WebContainer @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    private var mNightMode = false

    private var mMaskColor = Color.TRANSPARENT

    init {
        mNightMode = SettingUtil.getNightModeStatus().also {
            if (it)
                mMaskColor = ColorUtil.alpha(ContextCompat.getColor(getContext(), R.color.mask_color), 0.6f)
        }

    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (mNightMode)
            canvas.drawColor(mMaskColor)
    }
}