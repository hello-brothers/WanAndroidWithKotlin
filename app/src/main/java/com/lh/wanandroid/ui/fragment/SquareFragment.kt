package com.lh.wanandroid.ui.fragment

import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.SquareContract
import com.lh.wanandroid.mvp.presenter.SquarePresenter

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SquareFragment: BaseMvpFragment<SquareContract.View, SquareContract.Presenter>(), SquareContract.View {

    companion object{
        fun getInstance(): SquareFragment = SquareFragment()
    }
    override fun createPresenter(): SquareContract.Presenter  = SquarePresenter()

    override fun attachLayoutRes() = R.layout.fragment_square

    override fun lazyLoad() {
    }
}