package com.lh.wanandroid.rx

import com.lh.wanandroid.http.function.ErrorResume
import com.lh.wanandroid.http.function.ResponseFunction
import com.lh.wanandroid.mvp.model.bean.BaseBean
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.functions.Function

/**
 *@author: lh
 *CreateDate: 2020/7/16
 */
class RxUtil {

    companion object{
        fun <T> handleResult(): ObservableTransformer<HttpResult<T>, T>{
            return ObservableTransformer {
                it.onErrorResumeNext(ErrorResume())
                it.flatMap(ResponseFunction())
            }
        }
    }
}

