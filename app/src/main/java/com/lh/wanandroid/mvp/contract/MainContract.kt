package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
interface MainContract {

    interface View: IView{
        fun showLogoutSuccess(success: Boolean)

    }

    interface Presenter: IPresenter<View>{

        fun logout()
    }

    interface Model: IModel{

        fun logout(): Observable<HttpResult<Any?>>

    }

}