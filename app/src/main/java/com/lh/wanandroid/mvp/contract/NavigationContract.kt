package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.NavigationBean
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/8/4
 */
interface NavigationContract {
    interface View: IView, IScrollToTop{
        fun setNavigation(navigationList: List<NavigationBean>)
    }

    interface Presenter: IPresenter<View>{
        fun getNavigationTree()
    }

    interface Model: IModel{
        fun getNavigationTree(): Observable<HttpResult<List<NavigationBean>>>
    }
}