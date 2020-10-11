package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView

/**
 * Author: lh
 * Description:
 * Data: 2020/10/11
 */
interface ContentContract {

    interface View: IView{

    }

    interface Presenter: IPresenter<View>{

    }

    interface Model: IModel{

    }
}