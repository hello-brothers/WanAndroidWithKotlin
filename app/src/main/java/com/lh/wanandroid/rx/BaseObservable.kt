package com.lh.wanandroid.rx

import com.lh.wanandroid.R
import com.lh.wanandroid.app.App
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.http.exception.ApiException
import com.lh.wanandroid.mvp.model.bean.BaseBean
import com.lh.wanandroid.utils.isNetworkConnected
import io.reactivex.observers.ResourceObserver
import java.lang.RuntimeException

/**
 *@author: lh
 *CreateDate: 2020/7/15
 */
abstract class BaseObservable<T>(private val mView: IView?, private var bShowLoading: Boolean = true): ResourceObserver<T>() {

    override fun onStart() {
        super.onStart()
        if (bShowLoading) mView?.showLoading()
        if (!isNetworkConnected(App.context)){
            mView?.showDefaultMsg(App.instance.resources.getString(R.string.network_unavailable_tip))
            onComplete()
        }
    }

    override fun onComplete() {
        mView?.hideLoading()
    }

    override fun onNext(t: T) {
        mView?.hideLoading()
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        mView?.hideLoading()
        if (mView == null)throw RuntimeException("mView can not be null!")
        if (e is ApiException){
            mView?.showDefaultMsg(e.errorMsg)
        }
    }

    abstract fun onSuccess(t: T)
}