package com.lh.wanandroid.ui.fragment

import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.lh.wanandroid.HomeAdapter
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.presenter.HomePresenter
import com.lh.wanandroid.widget.SpaceItemDecoration
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomeFragment: BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View{


    companion object{
        fun newInstance(): HomeFragment = HomeFragment()
    }

    /** 是否为刷新状态 **/
    private var isRefresh = true
    private val datas = mutableListOf<Article>()

    /** recyclerview适配器 **/
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(datas)
    }

    /** 首页ItemDecoration **/
    private val spaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration()
    }

    override fun createPresenter() = HomePresenter()

    override fun attachLayoutRes(): Int  = R.layout.fragment_home

    override fun lazyLoad() {
        mPresenter?.requestArticles(0)
    }

    override fun initChildView(view: View) {
        mLayoutStatusView = multiple_status_view

        homeRecyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
            addItemDecoration(spaceItemDecoration)
        }

        homeAdapter.run {

            loadMoreModule.setOnLoadMoreListener {
                isRefresh = false
                swipeRefreshLayout.isRefreshing = false
                val pageIndex = homeAdapter.data.size / 20
                mPresenter?.requestArticles(pageIndex)
            }

        }
    }

    override fun setArticles(articles: ArticleResponseBody) {

//        if (isRefresh)
//            homeAdapter.setList(articles.datas)
//        else
//            homeAdapter.addData(articles.datas)
//
//        if (articles.size > articles.datas.size)
//            homeAdapter.loadMoreModule.loadMoreEnd(isRefresh)
//        else
//            homeAdapter.loadMoreModule.loadMoreComplete()

        articles.datas.let {
            homeAdapter.run {
                if (isRefresh)
                    setList(articles.datas)
                else
                    addData(articles.datas)

                this.loadMoreModule.let {module->
                    if (it.size < articles.size)
                        module.loadMoreEnd(isRefresh)
                    else
                        module.loadMoreComplete()
                }


            }
        }

    }
}