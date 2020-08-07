package com.lh.wanandroid.ext

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lh.wanandroid.app.App
import com.lh.wanandroid.widget.CustomToast

/**
 *@author: lh
 *CreateDate: 2020/8/7
 */
fun Activity.showToast(message: String){
    CustomToast(this, message).show()
}

fun Activity.showLongToast(message: String){
    CustomToast(this, message, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(message: String){
    CustomToast(activity!!.applicationContext, message).show()
}

fun Fragment.showLongToast(message: String){
    CustomToast(activity!!.applicationContext, message, Toast.LENGTH_LONG).show()
}

fun Context.showToast(message: String){
    CustomToast(this, message).show()
}

fun String.shortToast(){
    App.context.showToast(this)
}