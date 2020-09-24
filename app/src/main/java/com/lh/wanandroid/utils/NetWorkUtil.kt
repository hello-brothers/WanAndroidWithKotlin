package com.lh.wanandroid.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

/**
 *@author: lh
 *CreateDate: 2020/6/23
 */

fun isNetworkConnected(context: Context): Boolean{
    val manager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
        val network = manager.activeNetwork
        if (network != null){
            val networkCapabilities = manager.getNetworkCapabilities(network)
            networkCapabilities != null
        }else{
            false
        }
    }else{
        val info = manager.activeNetworkInfo;
        (info != null && info.isAvailable)
    }
}

class NetWorkUtil{

    companion object{
        fun isWifi(context: Context): Boolean {
            val manager =
                context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                val network = manager.activeNetwork


                network?.let {
                     manager.getNetworkCapabilities(it).hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                }?: false


            }else{
                val info = manager.activeNetworkInfo
                (info != null && info.type == ConnectivityManager.TYPE_WIFI)
            }
        }
    }
}