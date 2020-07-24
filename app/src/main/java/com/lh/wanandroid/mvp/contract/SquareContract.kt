package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
interface SquareContract {

    interface View: IView, IScrollToTop{

        fun setSquareList(article: ArticleResponseBody)
    }

    interface Presenter: IPresenter<View> {

        fun requestSquareList(page: Int)
    }

    interface Model: IModel {


        fun requestSquareList(page: Int): Observable<HttpResult<ArticleResponseBody>>
    }
}