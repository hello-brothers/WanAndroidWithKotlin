package com.lh.wanandroid.ui.fragment

import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.SystemContract
import com.lh.wanandroid.mvp.presenter.SystemPresenter

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SystemFragment: BaseMvpFragment<SystemContract.View, SystemContract.Presenter>(), SystemContract.View {

    companion object{
        fun getInstance() = SystemFragment()
    }
    override fun createPresenter(): SystemContract.Presenter  = SystemPresenter()

    override fun attachLayoutRes() = R.layout.fragment_system

    override fun lazyLoad() {
    }
}