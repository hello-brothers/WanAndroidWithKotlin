package com.lh.wanandroid.ui.setting

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.CheckBoxPreference
import androidx.preference.Preference
import com.afollestad.materialdialogs.color.ColorChooserDialog
import com.lh.wanandroid.R
import com.lh.wanandroid.event.RefreshEvent
import com.lh.wanandroid.ext.shortToast
import com.lh.wanandroid.rx.SchedulerUtils
import com.lh.wanandroid.ui.BasePreferenceFragment
import com.lh.wanandroid.widget.IconPreference
import io.reactivex.Observable
import org.greenrobot.eventbus.EventBus
import java.util.concurrent.TimeUnit

/**
 * Author: lh
 * Description: 设置fragment
 * Data: 2020/9/15
 */
class SettingFragment private constructor(private val mActivity: SettingActivity): BasePreferenceFragment() {


    private lateinit var keyOfThemeColor: IconPreference

    companion object{
        fun get(activity: SettingActivity) = SettingFragment(activity)

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        //加载xml
        addPreferencesFromResource(R.xml.pre_setting)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findPreference<CheckBoxPreference>("switchOfAutoNightMode")?.setOnPreferenceChangeListener { _, _ ->
            postRefreshEvent()
            true
        }

        //设置主题颜色
        keyOfThemeColor = findPreference<IconPreference>("keyOfThemeColor") as IconPreference
        findPreference<IconPreference>("keyOfThemeColor")?.setOnPreferenceClickListener {
            ColorChooserDialog.Builder(mActivity , R.string.theme_color)
                .backButton(R.string.back)
                .doneButton(R.string.done)
                .cancelButton(R.string.cancel)
                .customButton(R.string.custom)
                .allowUserColorInputAlpha(false)
                .show()
            false
        }


        //版本号
        val version = resources.getString(R.string.current_version).plus(context?.packageManager?.getPackageInfo(context?.packageName, 0)?.versionName)
        findPreference<Preference>("version")?.summary = version
        findPreference<Preference>("version")?.setOnPreferenceClickListener {
            "check version".shortToast()
            false
        }

        findPreference<CheckBoxPreference>("switchOfTopArticle")?.setOnPreferenceChangeListener { preference, newValue ->
            postRefreshEvent()
            true
        }

        findPreference<Preference>("auto_nightMode")?.setOnPreferenceClickListener {
            mActivity.startWithFragment(AutoNightModeFragment::class.java.name)
            true
        }
    }

    private fun postRefreshEvent() {
        Observable.timer(100, TimeUnit.MILLISECONDS)
            .compose(SchedulerUtils.ioToMain())
            .subscribe {
                EventBus.getDefault().post(RefreshEvent(true))
            }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(changeCallback)
    }


    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(changeCallback)
    }
    private val changeCallback = object : SharedPreferences.OnSharedPreferenceChangeListener{
        override fun onSharedPreferenceChanged(
            sharedPreferences: SharedPreferences?,
            key: String?
        ) {
            key?:return
            if (key == "color"){
                keyOfThemeColor.setView()
            }
        }
    }

}