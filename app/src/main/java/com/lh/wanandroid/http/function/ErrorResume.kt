package com.lh.wanandroid.http.function

import com.lh.wanandroid.http.exception.ExceptionHandle
import com.lh.wanandroid.mvp.model.bean.BaseBean
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 *@author: lh
 *CreateDate: 2020/7/16
 * 处理非服务器产生的异常
 */
class ErrorResume<T>: Function<Throwable, ObservableSource< HttpResult<T>>> {
    override fun apply(t: Throwable): ObservableSource<HttpResult<T>> {
        return Observable.error(ExceptionHandle.handleException(t))
    }


}