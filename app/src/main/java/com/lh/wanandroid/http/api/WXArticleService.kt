package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

/**
 *@author: lh
 *CreateDate: 2020/7/24
 */
interface WXArticleService {


    /**
     * 获取公众号列表
     * https://wanandroid.com/wxarticle/chapters/json
     */
    @GET("wxarticle/chapters/json")
    fun getWXChapters(): Observable<HttpResult<MutableList<WXChapterBean>>>

    /**
     * 查看某个公众号历史数据
     * https://wanandroid.com/wxarticle/list/408/1/json
     */
    @GET("wxarticle/list/{cid}/{page}/json")
    fun getWXArticles(@Path("cid")cid: Int, @Path("page") page: Int): Observable<HttpResult<ArticleResponseBody>>


}