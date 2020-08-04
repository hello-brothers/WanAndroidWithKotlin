package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.NavigationContract
import com.lh.wanandroid.mvp.model.NavigationModel

/**
 *@author: lh
 *CreateDate: 2020/8/4
 */
class NavigationPresenter: BasePresenter<NavigationContract.Model, NavigationContract.View>(), NavigationContract.Presenter {

    override fun createModel() = NavigationModel()

    override fun getNavigationTree() {
        mModel?.getNavigationTree()?.deal(mView){
            mView?.setNavigation(it)
        }
    }
}