package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.ProjectContract
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class ProjectModel: BaseModel(), ProjectContract.Model {

    override fun getProjectTree() = RetrofitHelper.projectService.getProjectTree()
}