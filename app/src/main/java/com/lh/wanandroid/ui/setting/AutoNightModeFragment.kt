package com.lh.wanandroid.ui.setting

import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TimePicker
import androidx.preference.Preference
import com.lh.wanandroid.R
import com.lh.wanandroid.ui.BasePreferenceFragment
import com.lh.wanandroid.utils.SettingUtil

class AutoNightModeFragment : BasePreferenceFragment() {

    private lateinit var autoNight: Preference
    private lateinit var autoDay: Preference

    private lateinit var hoursOfDayModel: String
    private lateinit var minuteOfDayModel: String
    private lateinit var hoursOfNightModel: String
    private lateinit var minuteOfNightModel: String

    companion object{
        fun get() = AutoNightModeFragment()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.pre_autonight)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        autoDay = findPreference<Preference>("auto_day") as Preference
        autoNight = findPreference<Preference>("auto_night") as Preference

        setDefaultData()

        autoDay.setOnPreferenceClickListener {
            TimePickerDialog(activity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    SettingUtil.setHoursOfDayModel(if (hourOfDay > 9) hourOfDay.toString() else "0$hourOfDay")
                    SettingUtil.setMinuteOfDayModel(if (minute > 9) minute.toString() else "0$minute")
                    setDefaultData()


                },
                hoursOfDayModel.toInt(),
                minuteOfDayModel.toInt(),
                true
            ).run {
                show()
                getButton(DialogInterface.BUTTON_NEGATIVE).setText(R.string.cancel)
                getButton(DialogInterface.BUTTON_POSITIVE).setText(R.string.done)
            }
            false
        }

        autoNight.setOnPreferenceClickListener {
            TimePickerDialog(activity,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                    SettingUtil.setHoursOfNightModel(if (hourOfDay > 9)  hourOfDay.toString() else "0$hourOfDay")
                    SettingUtil.setMinuteOfNightModel(if (minute > 9) minute.toString() else "0$minute")
                    setDefaultData()
                },
                hoursOfNightModel.toInt(),
                minuteOfNightModel.toInt(),
                true
            ).run {
                show()
                getButton(DialogInterface.BUTTON_NEGATIVE).setText(R.string.cancel)
                getButton(DialogInterface.BUTTON_POSITIVE).setText(R.string.done)
            }
            false
        }

    }

    private fun setDefaultData() {

        hoursOfDayModel = SettingUtil.getHoursOfDayModel().toString()
        minuteOfDayModel = SettingUtil.getMinuteOfDayModel().toString()

        hoursOfNightModel = SettingUtil.getHoursOfNightModel().toString()
        minuteOfNightModel = SettingUtil.getMinuteOfNightModel().toString()

        autoNight.summary = "$hoursOfNightModel:$minuteOfNightModel"
        autoDay.summary = "$hoursOfDayModel:$minuteOfDayModel"
    }
}