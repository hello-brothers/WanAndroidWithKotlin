package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.SquareContract
import com.lh.wanandroid.mvp.model.SquareModel

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SquarePresenter: BasePresenter<SquareContract.Model, SquareContract.View>(), SquareContract.Presenter {
    override fun createModel() = SquareModel()
    override fun requestSquareList(page: Int) {
        mModel?.requestSquareList(page)?.deal(mView){
            mView?.setSquareList(it)
        }
    }
}