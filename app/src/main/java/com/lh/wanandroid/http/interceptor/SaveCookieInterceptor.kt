package com.lh.wanandroid.http.interceptor

import com.lh.wanandroid.constant.HttpConstant
import okhttp3.Interceptor
import okhttp3.Response

/**
 *@author: lh
 *CreateDate: 2020/7/13
 */
class SaveCookieInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val requestUrl = request.url().toString()
        val domain = request.url().host()

        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY) || requestUrl.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
            && response.headers(HttpConstant.SET_COOKIE_KEY).isNotEmpty()){
            val encodeCookie =
                HttpConstant.encodeCookie(response.headers(HttpConstant.SET_COOKIE_KEY))

            HttpConstant.saveCookie(requestUrl, domain, encodeCookie)
        }

        return response
    }


}