package com.lh.wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.R
import com.lh.wanandroid.adapter.ProjectListAdapter
import com.lh.wanandroid.base.BaseMvpListFragment
import com.lh.wanandroid.constant.Constant
import com.lh.wanandroid.mvp.contract.ProjectListContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.presenter.ProjectListPresenter
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 * Author: lh
 * Description:
 * Data: 2020/8/5
 */
class  ProjectListFragment: BaseMvpListFragment<ProjectListContract.View, ProjectListContract.Presenter, Article>(), ProjectListContract.View {

    private var cid = 0

    companion object{
        fun newInstance(cid: Int) = ProjectListFragment().apply {
            arguments = Bundle().apply {
                putInt(Constant.CONTENT_CID_KEY, cid)
            }
        }
    }

    override fun onRefreshList() {
        rvAdapter.loadMoreModule.isEnableLoadMore = false
        mPresenter?.getProjectList(0, cid)
    }

    override fun onLoadMore() {
        (rvAdapter.data.size/pageSize).run {
            mPresenter?.getProjectList(this, cid)
        }
    }

    override fun generateAdapter() = ProjectListAdapter(dataList = mutableListOf())

    override fun createPresenter() = ProjectListPresenter()

    override fun initChildView(view: View) {
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY)?: 0

    }

    override fun attachLayoutRes() = R.layout.fragment_projectlist

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getProjectList(0, cid)
    }

    override fun setProjectList(articles: ArticleResponseBody) {

        articles.datas.let {
            if (isRefresh){
                swipeRefreshLayout.isRefreshing = false
                rvAdapter.setList(it)
            }else
                rvAdapter.addData(it)

            if (articles.over)
                rvAdapter.loadMoreModule.loadMoreEnd(isRefresh)
            else
                rvAdapter.loadMoreModule.loadMoreComplete()

            if (rvAdapter.data.isEmpty())
                mLayoutStatusView?.showEmpty()
            else
                mLayoutStatusView?.showContent()
        }
    }

}