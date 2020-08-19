package com.lh.wanandroid.base

import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.constant.Constant
import com.lh.wanandroid.ext.showToast
import com.lh.wanandroid.utils.Preference

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
abstract class BaseMvpActivity<in V: IView, P: IPresenter<in V>>: BaseActivity(), IView {


    protected var mPresenter: P? = null

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
        initChildView()
    }

    /** 创建Presenter **/
    abstract fun createPresenter(): P

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.detachView()
        this.mPresenter = null
    }

    abstract fun initChildView()

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(errorMsg: String) {

    }

    override fun showDefaultMsg(msg: String) {
        showToast(msg)
    }

    override fun showMsg(msg: String) {

    }

    override fun autoRefresh() {

    }
}