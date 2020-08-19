package com.lh.wanandroid.http.function

import com.lh.wanandroid.http.exception.ApiException
import com.lh.wanandroid.http.exception.ErrorStatus
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

/**
 *@author: lh
 *CreateDate: 2020/8/19
 */
class LogoutResponseFunction<T>: Function<HttpResult<T>,  ObservableSource<T>>{
    override fun apply(t: HttpResult<T>): ObservableSource<T> =
        if (t.errorCode == ErrorStatus.SUCCESS){
            Observable.create {
                it.onNext("" as T)
                it.onComplete()
            }
        }else
            Observable.error(ApiException(t.errorCode, t.errorMsg))

}