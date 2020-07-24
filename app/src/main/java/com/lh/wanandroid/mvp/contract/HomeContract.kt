package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.Banner
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
interface HomeContract {

    interface View: IView, IScrollToTop{

        fun setArticles(articles: ArticleResponseBody)

        fun setBanner(banners: List<Banner>)
    }

    interface Presenter: IPresenter<View>{
        /** 请求文章 **/
        fun requestArticles(pageNum: Int)

        fun requestHomeData()

        fun requestBanner()
    }

    interface Model: IModel{

        fun requestArticles(pageNum: Int): Observable<HttpResult<ArticleResponseBody>>

        fun requestTopArticles(): Observable<HttpResult<MutableList<Article>>>

        fun requestBanner(): Observable<HttpResult<List<Banner>>>
    }
}