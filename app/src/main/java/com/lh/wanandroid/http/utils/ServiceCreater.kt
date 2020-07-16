package com.lh.wanandroid.http.utils

import com.lh.wanandroid.BuildConfig
import com.lh.wanandroid.app.App
import com.lh.wanandroid.constant.HttpConstant
import com.lh.wanandroid.http.interceptor.CacheInterceptor
import com.lh.wanandroid.http.interceptor.HeaderInterceptor
import com.lh.wanandroid.http.interceptor.SaveCookieInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *@author: lh
 *CreateDate: 2020/7/13
 * 保存 Cookie
 */
object ServiceCreator {

    private val baseUrl = "https://wanandroid.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(getOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)

    private fun getOkHttpClient() = OkHttpClient().newBuilder().apply{


        val cacheFile = File(App.context.cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        /** 设置拦截器 **/
        addInterceptor(HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG)level = HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        addInterceptor(HeaderInterceptor())
        addInterceptor(SaveCookieInterceptor())
        addInterceptor(CacheInterceptor())

        /** 添加缓存 **/
        cache(cache)

        /** 超时设置 **/
        connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)

        /** 错误重连 **/
        retryOnConnectionFailure(true)
    }.build()
}