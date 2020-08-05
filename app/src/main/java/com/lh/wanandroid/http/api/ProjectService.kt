package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *@author: lh
 *CreateDate: 2020/8/5
 */
interface ProjectService {

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     */
    @GET("project/tree/json")
    fun getProjectTree(): Observable<HttpResult<List<WXChapterBean>>>

    /**
     * 项目列表数据
     * https://www.wanandroid.com/project/list/1/json?cid=294
     */
    @GET("project/list/{page}/json")
    fun getProjectList(@Path("page") page: Int, @Query("cid") cid: Int): Observable<HttpResult<ArticleResponseBody>>
}