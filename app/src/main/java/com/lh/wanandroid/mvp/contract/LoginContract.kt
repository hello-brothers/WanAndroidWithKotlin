package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/8/6
 */
interface LoginContract {
    interface View: IView{
        fun loginSuccess(data: LoginData)
    }

    interface Presenter: IPresenter<View>{
        fun loginWanAndroid(userName: String, password: String)
    }

    interface Model: IModel{
        fun loginWanAndroid(userName: String, password: String): Observable<HttpResult<LoginData>>
    }
}