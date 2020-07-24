package com.lh.wanandroid.mvp.model

import com.lh.wanandroid.base.BaseModel
import com.lh.wanandroid.http.RetrofitHelper
import com.lh.wanandroid.mvp.contract.SquareContract
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SquareModel: BaseModel(), SquareContract.Model {
    override fun requestSquareList(page: Int) = RetrofitHelper.squareService.getSquareList(page)

}