package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
interface SystemContract {

    interface View: IView, IScrollToTop {

    }

    interface Presenter: IPresenter<View> {

    }

    interface Model: IModel {

    }
}