package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.SystemTreeContract
import com.lh.wanandroid.mvp.model.SystemTreeModel

/**
 * Author: lh
 * Description:
 * Data: 2020/8/4
 */
class SystemTreePresenter: BasePresenter<SystemTreeContract.Model, SystemTreeContract.View>(), SystemTreeContract.Presenter {
    override fun createModel() = SystemTreeModel()

    override fun getSystemTree() {
        mModel?.getSystemTree()?.deal(mView){
            mView?.setSystemTree(it)
        }
    }
}