package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.mvp.contract.WechatContract
import com.lh.wanandroid.mvp.model.WechatModel

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class WechatPresenter: BasePresenter<WechatContract.Model, WechatContract.View>(), WechatContract.Presenter  {
    override fun createModel() = WechatModel()
}