package com.lh.wanandroid.ui.fragment

import android.view.View
import com.google.android.material.tabs.TabLayout
import com.lh.wanandroid.R
import com.lh.wanandroid.adapter.ProjectPageAdapter
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.ProjectContract
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import com.lh.wanandroid.mvp.presenter.ProjectPresenter
import kotlinx.android.synthetic.main.fragment_project.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * 项目
 *@author: lh
 *CreateDate: 2020/7/3
 */
class ProjectFragment: BaseMvpFragment<ProjectContract.View, ProjectContract.Presenter>(), ProjectContract.View {

    companion object{
        fun getInstance() = ProjectFragment()
    }

    private val dataList = mutableListOf<WXChapterBean>()

    private val pageAdapter by lazy {
        ProjectPageAdapter(childFragmentManager, dataList)
    }

    override fun createPresenter(): ProjectContract.Presenter = ProjectPresenter()

    override fun attachLayoutRes() = R.layout.fragment_project

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getProjectTree()
    }

    override fun initChildView(view: View) {
        mLayoutStatusView = multiple_status_view

        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.setupWithViewPager(viewpager)

    }

    override fun setProjectTree(data: List<WXChapterBean>) {
        data.let {
            dataList.addAll(it)
            doAsync {
                Thread.sleep(10)
                uiThread {
                    viewpager.run {
                        adapter = pageAdapter
                        offscreenPageLimit = dataList.size
                    }
                }
            }
        }
        if (data.isEmpty())
            mLayoutStatusView?.showEmpty()
        else
            mLayoutStatusView?.showContent()
    }

    override fun scrollToTop() {

    }
}