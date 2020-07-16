package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *@author: lh
 *CreateDate: 2020/7/13
 * 首页相关
 */
interface HomeService {




    /**
     * 获取文章列表
     * https://www.wanandroid.com/article/list/1/json
     */
    @GET("article/list/{pageNum}/json")
    fun getArticles(@Path("pageNum") pageNum: Int): Observable<HttpResult<ArticleResponseBody>>
}