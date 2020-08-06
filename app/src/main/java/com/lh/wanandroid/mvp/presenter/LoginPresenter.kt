package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.mvp.contract.LoginContract
import com.lh.wanandroid.mvp.model.LoginModel

/**
 *@author: lh
 *CreateDate: 2020/8/6
 */
class LoginPresenter: BasePresenter<LoginContract.Model, LoginContract.View>(), LoginContract.Presenter {
    override fun createModel() = LoginModel()
}