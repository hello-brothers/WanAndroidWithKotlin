package com.lh.wanandroid.base.mvp

import io.reactivex.disposables.Disposable

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
interface IModel {

    fun addDisposable(disposable: Disposable?)

    fun onDetach()
}