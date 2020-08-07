package com.lh.wanandroid.http

import com.lh.wanandroid.http.api.*
import com.lh.wanandroid.http.utils.ServiceCreator

/**
 *@author: lh
 *CreateDate: 2020/7/13
 */
object RetrofitHelper {

    val homeService by lazy {
        ServiceCreator.create<HomeService>()
    }

    val squareService by lazy {
        ServiceCreator.create<SquareService>()
    }

    val wxArticleService by lazy {
        ServiceCreator.create<WXArticleService>()
    }

    val systemService by lazy {
        ServiceCreator.create<SystemService>()
    }

    val projectService by lazy {
        ServiceCreator.create<ProjectService>()
    }

    val loginAndRegisterService by lazy {
        ServiceCreator.create<LoginAndRegisterService>()
    }
}