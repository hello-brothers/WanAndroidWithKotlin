package com.lh.wanandroid.base

import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
abstract class BaseMvpActivity<in V: IView, P: IPresenter<in V>>: BaseActivity(), IView {

    protected var mPresenter: P? = null

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    /** 创建Presenter **/
    abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMsg: String) {

    }

    override fun showDefaultMsg(msg: String) {

    }

    override fun showMsg(msg: String) {

    }
}