package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@author: lh
 *CreateDate: 2020/7/23
 */
interface SquareService {


    /**
     * 广场列表数据
     * https://wanandroid.com/user_article/list/页码/json
     */
    @GET("user_article/list/{pageNum}/json")
    fun getSquareList(@Path("pageNum") pageNum: Int): Observable<HttpResult<ArticleResponseBody>>

}