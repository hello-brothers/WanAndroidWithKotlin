package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.ext.logoutDeal
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.model.MainModel

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
class MainPresenter: BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {
    override fun createModel() = MainModel()
    override fun logout() {
        mModel?.logout()?.logoutDeal(mView){
            mView?.showLogoutSuccess(success = true)
        }
    }

    override fun getUserInfo() {
        mModel?.getUserInfo()?.deal(mView){
            mView?.showUserInfo(it)
        }
    }

}