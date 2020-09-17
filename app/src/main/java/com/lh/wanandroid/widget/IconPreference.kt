package com.lh.wanandroid.widget

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.lh.wanandroid.R
import com.lh.wanandroid.utils.SettingUtil

class IconPreference(context: Context?, attrs: AttributeSet?) : Preference(context, attrs) {
    init {
        widgetLayoutResource = R.layout.item_icon_preference
    }

    private lateinit var circleImageView: CircleImageView


    override fun onBindViewHolder(holder: PreferenceViewHolder?) {
        super.onBindViewHolder(holder)
        circleImageView = holder!!.findViewById(R.id.cv_preview) as  CircleImageView

        setView()
    }

    //setView
    fun setView(){
        val color = SettingUtil.getColor()
        circleImageView.setBkColor(color)
    }
}