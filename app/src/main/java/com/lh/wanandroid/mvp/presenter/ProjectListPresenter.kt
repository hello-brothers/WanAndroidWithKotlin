package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.ProjectListContract
import com.lh.wanandroid.mvp.model.ProjectListModel

/**
 * Author: lh
 * Description:
 * Data: 2020/8/5
 */
class ProjectListPresenter: BasePresenter<ProjectListContract.Model, ProjectListContract.View>(), ProjectListContract.Presenter {
    override fun createModel() = ProjectListModel()

    override fun getProjectList(page: Int, cid: Int) {
        mModel?.getProjectList(page, cid)?.deal(mView){
            mView?.setProjectList(it)
        }
    }
}