package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.Banner
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

    /**
     * 获取顶置文章
     * https://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    fun getTopArticles(): Observable<HttpResult<MutableList<Article>>>

    /**
     * 滚动图
     * https://www.wanandroid.com/banner/json
     */
    @GET("banner/json")
    fun getBanner(): Observable<HttpResult<List<Banner>>>
}