package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
interface ProjectContract {

    interface View: IView {

    }

    interface Presenter: IPresenter<View> {

    }

    interface Model: IModel {

    }

}