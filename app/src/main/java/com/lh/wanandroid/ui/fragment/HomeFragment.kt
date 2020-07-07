package com.lh.wanandroid.ui.fragment

import android.view.View
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomeFragment: BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(){


    companion object{
        fun getInstance(): HomeFragment = HomeFragment()
    }

    override fun createPresenter() = HomePresenter()

    override fun attachLayoutRes(): Int  = R.layout.fragment_home

    override fun lazyLoad() {
    }

    override fun initChildView(view: View) {
        mLayoutStatusView = multiple_status_view
    }
}