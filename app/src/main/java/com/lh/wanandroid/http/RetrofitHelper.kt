package com.lh.wanandroid.http

import com.lh.wanandroid.http.api.HomeService
import com.lh.wanandroid.http.utils.ServiceCreator

/**
 *@author: lh
 *CreateDate: 2020/7/13
 */
object RetrofitHelper {

    val homeService by lazy {
        ServiceCreator.create<HomeService>()
    }
}