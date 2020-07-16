package com.lh.wanandroid.ui.fragment

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.lh.wanandroid.HomeAdapter
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.presenter.HomePresenter
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomeFragment: BaseMvpFragment<HomeContract.View, HomeContract.Presenter>(), HomeContract.View{


    companion object{
        fun newInstance(): HomeFragment = HomeFragment()
    }

    private val datas = mutableListOf<Article>()

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(datas)
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

        }
    }

    override fun setArticles(articles: ArticleResponseBody) {
        homeAdapter.setList(articles.datas)
    }
}