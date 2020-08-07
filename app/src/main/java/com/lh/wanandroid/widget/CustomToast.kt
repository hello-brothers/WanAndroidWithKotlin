package com.lh.wanandroid.widget

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.lh.wanandroid.R

/**
 *@author: lh
 *CreateDate: 2020/8/7
 */
class CustomToast(private val context: Context, private val message: String, private val mDuration: Int = Toast.LENGTH_SHORT) {

    private val customView by lazy {
        LayoutInflater.from(context).inflate(R.layout.toast_custom, null).apply {
            findViewById<TextView>(R.id.tvPrompt).text = message
        }
    }

    private val  toast by lazy{
        Toast(context).apply {
            duration = mDuration
            view = customView
            setGravity(Gravity.CENTER, 0, 0)
        }
    }

    fun show(){
        toast.show()
    }
}