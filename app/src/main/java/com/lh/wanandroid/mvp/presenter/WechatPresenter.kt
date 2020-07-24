package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.WechatContract
import com.lh.wanandroid.mvp.model.WechatModel

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class WechatPresenter: BasePresenter<WechatContract.Model, WechatContract.View>(), WechatContract.Presenter  {
    override fun createModel() = WechatModel()
    override fun requestWXChapters() {
        mModel?.requestWXChapters()?.deal(mView){
            mView?.setWXChapters(it)
        }

    }
}