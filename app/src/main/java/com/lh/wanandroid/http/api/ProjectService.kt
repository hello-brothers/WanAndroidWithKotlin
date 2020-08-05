package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable
import retrofit2.http.GET

/**
 *@author: lh
 *CreateDate: 2020/8/5
 */
interface ProjectService {

    /**
     * 项目分类
     * https://www.wanandroid.com/project/tree/json
     */
    @GET("https://www.wanandroid.com/project/tree/json")
    fun getProjectTree(): Observable<HttpResult<List<WXChapterBean>>>
}