package com.lh.wanandroid.base

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 *@author: lh
 *CreateDate: 2020/7/23
 */

abstract class BaseMvpListFragment<in V: IView, P: IPresenter<V>> : BaseMvpFragment<V, P>(){

    /** 是否为下拉刷新 **/
    protected var isRefresh = true

    protected val pageSize = 20
    /** ItemDecoration **/
    private val spaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration()
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
        }

        swipeRefreshLayout.setOnRefreshListener {
            isRefresh = true
            onRefreshList()
        }
        super.initView(view)
    }

    /** 刷新列表数据源 **/
    abstract fun onRefreshList()

}