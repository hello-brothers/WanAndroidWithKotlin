package com.lh.wanandroid.mvp.contract

import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import io.reactivex.Observable

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
interface WechatContract {
    interface View: IView, IScrollToTop{

        fun setWXChapters(chapters: List<WXChapterBean>)
    }

    interface Presenter: IPresenter<View>{

        fun requestWXChapters()
    }

    interface Model: IModel{

        fun requestWXChapters(): Observable<HttpResult<MutableList<WXChapterBean>>>
    }
}