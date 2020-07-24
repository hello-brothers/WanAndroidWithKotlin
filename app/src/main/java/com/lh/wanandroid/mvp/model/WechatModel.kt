package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.WechatContract
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class WechatModel: BaseModel(), WechatContract.Model {

    override fun requestWXChapters() = RetrofitHelper.wxArticleService.getWXChapters()

}