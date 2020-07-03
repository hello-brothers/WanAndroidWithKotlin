package com.lh.wanandroid.ui.fragment

import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.WechatContract
import com.lh.wanandroid.mvp.presenter.WechatPresenter

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class WechatFragment: BaseMvpFragment<WechatContract.View, WechatContract.Presenter>(), WechatContract.View {

    companion object{
        fun getInstance() = WechatFragment()
    }
    override fun createPresenter(): WechatContract.Presenter  = WechatPresenter()

    override fun attachLayoutRes() = R.layout.fragment_wechat

    override fun lazyLoad() {
    }
}