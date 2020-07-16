package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.HomeModel
import com.lh.wanandroid.rx.SchedulerUtils
import com.lh.wanandroid.rx.scheduler.IoMainScheduler

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomePresenter: BasePresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter {

    override fun createModel() = HomeModel()

    override fun requestArticles(pageNum: Int) {

        mModel?.requestArticles(pageNum)?.deal(mView){
            mView?.setArticles(it)
        }

    }
}