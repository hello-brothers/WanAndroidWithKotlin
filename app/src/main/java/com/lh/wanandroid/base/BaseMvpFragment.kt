package com.lh.wanandroid.base

import android.view.View
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
abstract class BaseMvpFragment<in V: IView, P: IPresenter<V>>: BaseFragment(), IView {

    protected var mPresenter: P? = null

    protected abstract fun createPresenter(): P

    override fun initView(view: View) {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
        initChildView(view)
    }

    abstract fun initChildView(view: View)

    override fun onDestroyView() {
        super.onDestroyView()
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