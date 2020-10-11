package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.mvp.contract.ContentContract
import com.lh.wanandroid.mvp.model.ContentModel

/**
 * Author: lh
 * Description:
 * Data: 2020/10/11
 */
class ContentPresenter: BasePresenter<ContentContract.Model, ContentContract.View>(), ContentContract.Presenter {
    override fun createModel() = ContentModel()
}