package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.Banner
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomeModel: BaseModel(), HomeContract.Model{


    override fun requestArticles(pageNum: Int): Observable<HttpResult<ArticleResponseBody>>  = RetrofitHelper.homeService.getArticles(pageNum)


    override fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>> = RetrofitHelper.homeService.getTopArticles()

    override fun requestBanner(): Observable<HttpResult<List<Banner>>>  = RetrofitHelper.homeService.getBanner()

}