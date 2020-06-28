package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.model.MainModel

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
class MainPresenter: BasePresenter<MainContract.Model, MainContract.View>(), MainContract.Presenter {
    override fun createModel() = MainModel()

}