package com.lh.wanandroid.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.lh.wanandroid.app.App
import com.lh.wanandroid.ext.showToast

/**
 *@author: lh
 *CreateDate: 2020/6/24
 */

/** activity跳转 **/
inline fun <reified T> cStartActivity(context: Context){
    cStartActivityWithData<T>(context){}
}

inline fun <reified T> cStartActivityWithData(context: Context, block: Intent.() -> Unit){
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}




