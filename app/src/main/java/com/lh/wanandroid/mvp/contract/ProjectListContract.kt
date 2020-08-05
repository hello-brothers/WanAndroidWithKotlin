package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 * Author: lh
 * Description:
 * Data: 2020/8/5
 */
interface ProjectListContract {
    interface View: IView{
        fun setProjectList(articles: ArticleResponseBody)
    }

    interface Presenter: IPresenter<View>{
        fun getProjectList(page: Int, cid: Int)
    }

    interface Model: IModel{
        fun getProjectList(page: Int, cid: Int): Observable<HttpResult<ArticleResponseBody>>
    }
}