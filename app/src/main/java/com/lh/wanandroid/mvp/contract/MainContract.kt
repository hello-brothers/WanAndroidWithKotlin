package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.UserInfoBody
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
interface MainContract {

    interface View: IView{
        fun showLogoutSuccess(success: Boolean)

        fun showUserInfo(userInfo: UserInfoBody)
    }

    interface Presenter: IPresenter<View>{

        fun logout()

        fun getUserInfo()
    }

    interface Model: IModel{

        fun logout(): Observable<HttpResult<Any?>>

        fun getUserInfo(): Observable<HttpResult<UserInfoBody>>
    }

}