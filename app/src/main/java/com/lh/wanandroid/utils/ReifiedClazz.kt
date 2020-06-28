package com.lh.wanandroid.utils

import android.content.Context
import android.content.Intent

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