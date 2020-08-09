package com.lh.wanandroid.ui.fragment

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import cn.bingoogolapple.bgabanner.BGABanner
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.adapter.HomeAdapter
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpListFragment
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.Banner
import com.lh.wanandroid.mvp.presenter.HomePresenter
import com.lh.wanandroid.utils.ImageLoader
import io.reactivex.Observable
import kotlinx.android.synthetic.main.item_home_banner.view.*
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomeFragment: BaseMvpListFragment<HomeContract.View, HomeContract.Presenter, Article>(), HomeContract.View{


    companion object{
        fun newInstance(): HomeFragment = HomeFragment()
    }

    /** banner **/
    private val bannerView: View by lazy {
        layoutInflater.inflate(R.layout.item_home_banner, null)
    }


    /** banner data **/
    private lateinit var bannerDatas: ArrayList<Banner>

    private val datas = mutableListOf<Article>()

    /** banner adapter **/
    private val bannerAdapter:BGABanner.Adapter<ImageView, String> by lazy {
        BGABanner.Adapter<ImageView, String>{banner, itemView, model, position ->
            activity?.let { ImageLoader.load(it, model, itemView) }

        }
    }

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
        mLayoutStatusView?.showLoading()
        mPresenter?.requestHomeData()
    }

    override fun initChildView(view: View) {

        bannerView.banner.run {
            setDelegate(bannerDelegate)
        }

        rvAdapter.run {
            addHeaderView(bannerView)
        }
    }

    override fun onRefreshList() {
        mPresenter?.requestHomeData()
    }

    override fun setArticles(articles: ArticleResponseBody) {

        if (isRefresh){
            rvAdapter.setList(articles.datas)
            swipeRefreshLayout.isRefreshing = false
        }
        else
            rvAdapter.addData(articles.datas)

        if (articles.size > articles.datas.size)
            rvAdapter.loadMoreModule.loadMoreEnd(isRefresh)
        else
            rvAdapter.loadMoreModule.loadMoreComplete()

        if (rvAdapter.data.isEmpty())
            mLayoutStatusView?.showEmpty()
        else
            mLayoutStatusView?.showContent()

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

    override fun scrollToTop() {
        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20)
                scrollToPosition(0)
            else
                smoothScrollToPosition(0)
        }
    }

    override fun onRefresh() {
        onRefreshList()

    }

    override fun onLoadMore() {
        val pageIndex = rvAdapter.data.size / 20
        mPresenter?.requestArticles(pageIndex)
    }

    override fun generateAdapter() = HomeAdapter(datas)


}