package com.lh.wanandroid.base.mvp

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
interface IPresenter<in V : IView> {

    /** 绑定View **/
    fun attachView(mView: V)

    /** 解绑View **/
    fun detachView()

}