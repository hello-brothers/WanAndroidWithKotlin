package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.NavigationContract
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.NavigationBean
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/8/4
 */
class NavigationModel: BaseModel(), NavigationContract.Model {

    override fun getNavigationTree() = RetrofitHelper.systemService.getNavigationTree()
}