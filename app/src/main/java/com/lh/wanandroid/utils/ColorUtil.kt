package com.lh.wanandroid.utils

import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red

/**
 * Author: lh
 * Description:
 * Data: 2020/10/12
 */
object ColorUtil {

    fun alpha(@ColorInt color: Int, @IntRange(from = 0, to = 255) alpha: Int): Int{
        val red = color.red
        val green = color.green
        val blue = color.blue
        return Color.argb(alpha, red, green, blue)
    }

    fun alpha(@ColorInt color: Int, @FloatRange(from = 0.0, to = 1.0) alpha: Float) = alpha(color, (255*alpha).toInt())
}