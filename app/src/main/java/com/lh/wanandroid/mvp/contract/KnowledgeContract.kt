package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/8/3
 */
interface KnowledgeContract {

    interface View: IView{
        fun setWXArticles(articles: ArticleResponseBody)
    }

    interface Presenter: IPresenter<View>{
        fun getWXArticles(cid: Int, page: Int)
    }

    interface Model: IModel{
        fun getWXArticles(cid: Int, page: Int): Observable<HttpResult<ArticleResponseBody>>
    }

}