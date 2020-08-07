package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.LoginContract
import com.lh.wanandroid.mvp.model.LoginModel

/**
 *@author: lh
 *CreateDate: 2020/8/6
 */
class LoginPresenter: BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter {
    override fun createModel() = LoginModel()

    override fun loginWanAndroid(userName: String, password: String) {
        mModel?.loginWanAndroid(userName, password)?.deal(mView){
            mView?.loginSuccess(it)
        }
    }
}