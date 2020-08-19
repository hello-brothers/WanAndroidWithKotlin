package com.lh.wanandroid.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 *@author: lh
 *CreateDate: 2020/7/23
 */

abstract class BaseMvpListFragment<in V: IView, P: IPresenter<V>, B> : BaseMvpFragment<V, P>(){

    /** 是否为下拉刷新 **/
    protected var isRefresh = true

    protected val pageSize = 20
    /** ItemDecoration **/
    private val spaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration()
    }

    protected val rvAdapter by lazy {
        generateAdapter()
    }
    /** linearLayoutManager **/
    protected val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    override fun initView(view: View) {
        mLayoutStatusView = multiple_status_view
        recyclerView.run {
            layoutManager = linearLayoutManager
            addItemDecoration(spaceItemDecoration)
            adapter = rvAdapter
        }

        swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            rvAdapter.loadMoreModule.isEnableLoadMore = false
            onRefreshList()
        }

        if (isLoadMore)
            rvAdapter.loadMoreModule.setOnLoadMoreListener {
                isRefresh = false
                swipeRefreshLayout.isRefreshing = false
                onLoadMore()
            }

        super.initView(view)
    }

    /** 刷新列表数据源 **/
    abstract fun onRefreshList()

    /** 加载更多 **/
    abstract fun onLoadMore()

    abstract fun generateAdapter(): BaseQuickAdapter<B, BaseViewHolder>

    /** 是否支持加载更多 默认为支持加载更多（此时adapter需要实现LoadMoreModule接口） **/
    open val isLoadMore = true

    override fun autoRefresh() {
        super.autoRefresh()
        onRefreshList()
    }
}