package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.KnowledgeContract
import com.lh.wanandroid.mvp.model.KnowledgeModel

/**
 *@author: lh
 *CreateDate: 2020/8/3
 */
class KnowledgePresenter: BasePresenter<KnowledgeContract.Model, KnowledgeContract.View>(), KnowledgeContract.Presenter{

    override fun createModel() = KnowledgeModel()
    override fun getWXArticles(cid: Int, page: Int) {

        mModel?.getWXArticles(cid, page)?.deal(mView){
            mView?.setWXArticles(it)
        }
    }


}