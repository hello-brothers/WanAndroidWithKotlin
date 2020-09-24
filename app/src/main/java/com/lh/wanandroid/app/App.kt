package com.lh.wanandroid.app

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.lh.wanandroid.utils.CommonUtil
import com.lh.wanandroid.utils.DayNightModeUtil
import com.lh.wanandroid.utils.SettingUtil
import org.jetbrains.anko.appcompat.v7.Appcompat
import java.util.*
import kotlin.properties.Delegates

/**
 *@author: lh
 *CreateDate: 2020/6/24
 */
class App: Application(){

    companion object{
        val TAG = "wanAndroid"
        var context: Context by Delegates.notNull()

        lateinit var instance: Application
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        context = applicationContext

        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)

        initTheme()
    }

    private val mActivityLifecycleCallbacks = object : ActivityLifecycleCallbacks{
        override fun onActivityPaused(activity: Activity) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        }

        override fun onActivityStopped(activity: Activity) {
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        }

        override fun onActivityResumed(activity: Activity) {
        }

    }

    /** theme **/
    private fun initTheme() {
        if (SettingUtil.getIsAutoNightMode()){

            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
            val currentMinute = calendar.get(Calendar.MINUTE)
            val currentTime = currentHour*60 + currentMinute

            val hoursOfDayModel = SettingUtil.getHoursOfDayModel().toInt()
            val minuteOfDayModel = SettingUtil.getMinuteOfDayModel().toInt()
            val timeOfDayModel = hoursOfDayModel * 60 + minuteOfDayModel

            val hoursOfNightModel = SettingUtil.getHoursOfNightModel().toInt()
            val minuteOfNightModel = SettingUtil.getMinuteOfNightModel().toInt()
            val timeOfNightModel = hoursOfNightModel*60 + minuteOfNightModel

            if (currentTime < timeOfDayModel || currentTime >= timeOfNightModel){
                DayNightModeUtil.setNightModeEnable(true)
            }else{
                DayNightModeUtil.setNightModeEnable(false)
            }
        }else{
            DayNightModeUtil.applyDayNightMode()
        }
    }
}