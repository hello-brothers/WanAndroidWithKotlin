package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.LoginContract
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.LoginData
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/8/6
 */
class LoginModel: BaseModel(), LoginContract.Model {

    override fun loginWanAndroid(
        userName: String,
        password: String
    ) = RetrofitHelper.loginAndRegisterService.loginWanAndroid(userName, password)
}