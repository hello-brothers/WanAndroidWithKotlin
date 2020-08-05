package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.ProjectContract
import com.lh.wanandroid.mvp.contract.ProjectListContract
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable

/**
 * Author: lh
 * Description:
 * Data: 2020/8/5
 */
class ProjectListModel: BaseModel(), ProjectListContract.Model {
    override fun getProjectList(page: Int, cid: Int) = RetrofitHelper.projectService.getProjectList(page, cid)
}