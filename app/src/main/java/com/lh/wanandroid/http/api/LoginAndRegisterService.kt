package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 *@author: lh
 *CreateDate: 2020/8/7
 */
interface LoginAndRegisterService {
    /**
     * 登录
     * https://www.wanandroid.com/user/login
     * @POST注解表示请求方式为POST，通常和@FormUrlEncoded注解一起使用，在使用@FormUrlEncoded时可以使用@Field标识表单字段
     */
    @POST("user/login")
    @FormUrlEncoded
    fun loginWanAndroid(@Field("username")username: String, @Field("password") password: String): Observable<HttpResult<LoginData>>

    /**
     * 登出
     * https://www.wanandroid.com/user/logout/json
     */
    @GET("user/logout/json")
    fun logout(): Observable<HttpResult<Any?>>
}