package com.lh.wanandroid.rx.scheduler

import io.reactivex.*

/**
 *@author: lh
 *CreateDate: 2020/7/14
 */
abstract class BaseScheduler<T>(private val subscribeObScheduler: Scheduler, private val observeOnScheduler: Scheduler): ObservableTransformer<T, T> {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream.subscribeOn(subscribeObScheduler)
            .observeOn(observeOnScheduler)
    }
}