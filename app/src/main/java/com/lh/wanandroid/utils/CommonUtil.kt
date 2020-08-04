package com.lh.wanandroid.utils

import android.graphics.Color
import java.util.*

/**
 *@author: lh
 *CreateDate: 2020/8/4
 */
object CommonUtil {

    fun randomColor():Int{
        val random = Random()
        var red = random.nextInt(190)
        var green = random.nextInt(190)
        var blue = random.nextInt(190)
        //todo 暗黑模式
        if (false){
            red = random.nextInt(105) + 150
            green = random.nextInt(105) + 150
            blue = random.nextInt(105) + 150
        }
        return Color.rgb(red, green, blue)
    }
}