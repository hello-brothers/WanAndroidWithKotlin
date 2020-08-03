package com.lh.wanandroid.http.api

import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Author: lh
 * Description:
 * Data: 2020/8/3
 */
interface SystemService {

    /**
     * 体系数据
     * https://www.wanandroid.com/tree/json
     */
    @GET("https://www.wanandroid.com/tree/json")
    fun getSystemTree(): Observable<HttpResult<List<KnowledgeTreeBody>>>
}