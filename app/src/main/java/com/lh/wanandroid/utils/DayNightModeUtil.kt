package com.lh.wanandroid.utils

import androidx.appcompat.app.AppCompatDelegate

/**
 * @Des
 *
 * @author lh
 * @data 2020/9/15
 */
object DayNightModeUtil {

    fun setNightModeEnable(enabled: Boolean){
        SettingUtil.setNightModeStatus(enabled)
        applyDayNightMode()
    }

    /** 根据配置开启关闭夜间模式 **/
    fun applyDayNightMode(){
        if (SettingUtil.getNightModeStatus()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}