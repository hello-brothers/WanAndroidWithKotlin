package com.lh.wanandroid.http.interceptor

import com.lh.wanandroid.constant.HttpConstant
import com.lh.wanandroid.utils.Preference
import okhttp3.Interceptor
import okhttp3.Response

/**
 *@author: lh
 *CreateDate: 2020/7/13
 * 设置请求头
 */

class HeaderInterceptor: Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()


        builder.addHeader("Content-Type", "application/json; charset=utf-8")


        val domain = request.url().host().toString()
        val url = request.url().toString()

        if (domain.isNotEmpty() && (url.isSupportUrl())){
            val spDomain by Preference(domain, "")
            val cookie = if (spDomain.isNotEmpty()) spDomain else ""
            if (cookie.isNotEmpty()){
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }
        return chain.proceed(builder.build())
    }

    private fun String.isSupportUrl(): Boolean{
        return contains(HttpConstant.ARTICLE_WEBSITE) ||
                contains(HttpConstant.COLLECTIONS_WEBSITE)||
                contains(HttpConstant.COIN_WEBSITE)||
                contains(HttpConstant.UNCOLLECTIONS_WEBSITE)||
                contains(HttpConstant.TODO_WEBSITE)
    }

}