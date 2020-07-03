package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.HomeModel

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomePresenter: BasePresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter {
    override fun createModel() = HomeModel()
}