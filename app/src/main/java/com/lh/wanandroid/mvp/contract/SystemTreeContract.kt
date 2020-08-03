package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable

/**
 * Author: lh
 * Description:
 * Data: 2020/8/3
 */
interface SystemTreeContract {

    interface View: IView{
        fun setSystemTree(knowledgeList: List<KnowledgeTreeBody>)
    }

    interface Presenter: IPresenter<View>{
        fun getSystemTree()
    }

    interface Model: IModel{
        fun getSystemTree(): Observable<HttpResult<List<KnowledgeTreeBody>>>
    }
}