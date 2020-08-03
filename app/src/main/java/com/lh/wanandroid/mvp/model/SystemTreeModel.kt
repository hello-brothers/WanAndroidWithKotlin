package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.SystemContract
import com.lh.wanandroid.mvp.contract.SystemTreeContract
import com.lh.wanandroid.mvp.model.bean.KnowledgeTreeBody
import io.reactivex.Observable

/**
 * Author: lh
 * Description:
 * Data: 2020/8/4
 */
class SystemTreeModel: BaseModel(), SystemTreeContract.Model {
    override fun getSystemTree() = RetrofitHelper.systemService.getSystemTree()
}