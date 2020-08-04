package com.lh.wanandroid.ui.fragment

import android.view.View
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.ProjectContract
import com.lh.wanandroid.mvp.presenter.ProjectPresenter

/**
 * 项目
 *@author: lh
 *CreateDate: 2020/7/3
 */
class ProjectFragment: BaseMvpFragment<ProjectContract.View, ProjectContract.Presenter>(), ProjectContract.View {

    companion object{
        fun getInstance() = ProjectFragment()
    }
    override fun createPresenter(): ProjectContract.Presenter = ProjectPresenter()

    override fun attachLayoutRes() = R.layout.fragment_project

    override fun lazyLoad() {

    }

    override fun initChildView(view: View) {

    }

    override fun scrollToTop() {

    }
}