package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.ProjectContract
import com.lh.wanandroid.mvp.model.ProjectModel

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class ProjectPresenter: BasePresenter<ProjectContract.Model, ProjectContract.View>(), ProjectContract.Presenter {
    override fun createModel() = ProjectModel()
    override fun getProjectTree() {
        mModel?.getProjectTree()?.deal(mView){
            mView?.setProjectTree(it)
        }
    }
}