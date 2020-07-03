package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.mvp.contract.SystemContract
import com.lh.wanandroid.mvp.model.SystemModel

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SystemPresenter: BasePresenter<SystemContract.Model, SystemContract.View>(), SystemContract.Presenter {
    override fun createModel() = SystemModel()
}