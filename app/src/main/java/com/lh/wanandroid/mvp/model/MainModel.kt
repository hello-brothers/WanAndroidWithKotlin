package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.UserInfoBody
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
class MainModel: BaseModel(), MainContract.Model{

    override fun logout() = RetrofitHelper.loginAndRegisterService.logout()

    override fun getUserInfo() = RetrofitHelper.loginAndRegisterService.getUserInfo()

}