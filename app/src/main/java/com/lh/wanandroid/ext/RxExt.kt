package com.lh.wanandroid.ext

import android.annotation.SuppressLint
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.http.exception.ErrorStatus
import com.lh.wanandroid.http.function.ReTryWithDelay
import com.lh.wanandroid.mvp.model.bean.BaseBean
import com.lh.wanandroid.mvp.model.bean.HttpResult
import com.lh.wanandroid.rx.BaseObservable
import com.lh.wanandroid.rx.RxUtil
import com.lh.wanandroid.rx.SchedulerUtils
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.observers.ResourceObserver

/**
 *@author: lh
 *CreateDate: 2020/7/15
 */

@SuppressLint("CheckResult")
fun <T> Observable<HttpResult<T>>.deal(mView: IView?, onSuccess: (T)-> Unit) {

     compose(SchedulerUtils.ioToMain())//线程切换
         .retryWhen(ReTryWithDelay())//重连
         .compose(RxUtil.handleResult())//处理数据
         .subscribeWith(object : BaseObservable<T>(mView){
             override fun onSuccess(t: T) {
                 onSuccess.invoke(t)
             }


         })





}





