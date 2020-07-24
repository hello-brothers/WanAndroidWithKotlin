package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable
import retrofit2.http.GET

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
}