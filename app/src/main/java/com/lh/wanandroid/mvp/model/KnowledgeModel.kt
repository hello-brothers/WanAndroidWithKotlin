package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.KnowledgeContract
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/8/3
 */
class KnowledgeModel: BaseModel(), KnowledgeContract.Model {

    override fun getWXArticles(cid: Int, page: Int)
            = RetrofitHelper.wxArticleService.getWXArticles(cid, page)
}