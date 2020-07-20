package com.lh.wanandroid.ui.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cn.bingoogolapple.bgabanner.BGABanner
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.lh.wanandroid.HomeAdapter
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.Banner
import com.lh.wanandroid.mvp.presenter.HomePresenter
import com.lh.wanandroid.utils.ImageLoader
import com.lh.wanandroid.widget.SpaceItemDecoration
import io.reactivex.Observable
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_home_banner.view.*

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

    /** banner **/
    private val bannerView: View by lazy {
        layoutInflater.inflate(R.layout.item_home_banner, null)
    }

    /** 首页ItemDecoration **/
    private val spaceItemDecoration: SpaceItemDecoration by lazy {
        SpaceItemDecoration()
    }

    /** banner adapter **/
    private val bannerAdapter:BGABanner.Adapter<ImageView, String> by lazy {
        BGABanner.Adapter<ImageView, String>{banner, itemView, model, position ->
            activity?.let { ImageLoader.load(it, model, itemView) }

        }
    }

    /** banner data **/
    private lateinit var bannerDatas: ArrayList<Banner>

    /** banner lick callback **/
    private val bannerDelegate: BGABanner.Delegate<ImageView, String> by lazy {
        BGABanner.Delegate<ImageView, String>{banner, itemView, model, position ->
            if (bannerDatas.size > 0){
                val data = bannerDatas[position]
            }

        }
    }

    override fun createPresenter() = HomePresenter()

    override fun attachLayoutRes(): Int  = R.layout.fragment_home

    override fun lazyLoad() {
        mPresenter?.requestHomeData()
    }

    override fun initChildView(view: View) {
        mLayoutStatusView = multiple_status_view

        homeRecyclerView.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = homeAdapter
            addItemDecoration(spaceItemDecoration)
        }

        bannerView.banner.run {
            setDelegate(bannerDelegate)
        }

        homeAdapter.run {

            addHeaderView(bannerView)
            loadMoreModule.setOnLoadMoreListener {
                isRefresh = false
                swipeRefreshLayout.isRefreshing = false
                val pageIndex = homeAdapter.data.size / 20
                mPresenter?.requestArticles(pageIndex)
            }
        }

        swipeRefreshLayout.run {
            setOnRefreshListener {
                isRefresh =true
                homeAdapter.loadMoreModule.isEnableLoadMore = false
                mPresenter?.requestHomeData()
            }
        }


    }

    override fun setArticles(articles: ArticleResponseBody) {

        if (isRefresh){
            homeAdapter.setList(articles.datas)
            swipeRefreshLayout.isRefreshing = false
        }
        else
            homeAdapter.addData(articles.datas)

        if (articles.size > articles.datas.size)
            homeAdapter.loadMoreModule.loadMoreEnd(isRefresh)
        else
            homeAdapter.loadMoreModule.loadMoreComplete()
    }

    @SuppressLint("CheckResult")
    override fun setBanner(banners: List<Banner>) {

        val bannerFeedList = ArrayList<String>()
        val bannerTitleList = ArrayList<String>()


        Observable.fromIterable(banners)
            .subscribe {
                bannerFeedList.add(it.imagePath)
                bannerTitleList.add(it.title)
            }

        bannerView.banner.run {
            setAutoPlayAble(bannerFeedList.size > 1)
            setData(bannerFeedList, bannerTitleList)
            setAdapter(bannerAdapter)
        }
    }


}