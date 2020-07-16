package com.lh.wanandroid.http.interceptor

import com.lh.wanandroid.app.App
import com.lh.wanandroid.utils.isNetworkConnected
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 *@author: lh
 *CreateDate: 2020/7/13
 * 设置缓存
 */
class CacheInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()


        if (!isNetworkConnected(App.context)){
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
        }

        var response = chain.proceed(request)


        if (isNetworkConnected(App.context)){
            //有网络时，设置缓存时间为0小时，也就是不设置缓存，仅对get有用

            val maxAge = 60 * 3
            response.newBuilder()
                .header("Cache-control", "public, max-age=$maxAge")
                .removeHeader("Retrofit")
                .build()
        }else{
            //无网络时，设置超时未3周
            val maxStyle = 60 * 60 * 24 * 7 * 3
            response.newBuilder()
                .header("Cache-control", "public, only-if-cache, max-style=$maxStyle")
                .removeHeader("nyn")
                .build()
        }
        return response
    }
}