package com.lh.wanandroid.ui.activity

import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.mvp.contract.LoginContract
import com.lh.wanandroid.mvp.presenter.LoginPresenter

/**
 *@author: lh
 *CreateDate: 2020/8/6
 */
class LoginActivity: BaseMvpActivity<LoginContract.View, LoginContract.Presenter>(), LoginContract.View {
    override fun createPresenter() = LoginPresenter()

    override fun attachLayoutRes() = R.layout.activity_login

    override fun initData() {

    }

    override fun start() {

    }
}