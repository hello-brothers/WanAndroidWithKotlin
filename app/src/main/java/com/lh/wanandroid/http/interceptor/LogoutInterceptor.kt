package com.lh.wanandroid.http.interceptor

import android.util.Log
import com.lh.wanandroid.constant.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response

/**
 *@author: lh
 *CreateDate: 2020/8/19
 */
class LogoutInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val requestUrl = request.url().toString()

        if (requestUrl.contains(HttpConstant.USER_LOGOUT_KEY)){

            Log.d("LogoutInterceptor", response.body().toString())
        }

        return response
    }
}