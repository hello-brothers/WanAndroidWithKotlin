package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
interface HomeContract {

    interface View: IView{

        fun setArticles(articles: ArticleResponseBody)
    }

    interface Presenter: IPresenter<View>{
        /** 请求文章 **/
        fun requestArticles(pageNum: Int)
    }

    interface Model: IModel{

        fun requestArticles(pageNum: Int): Observable<HttpResult<ArticleResponseBody>>
    }
}