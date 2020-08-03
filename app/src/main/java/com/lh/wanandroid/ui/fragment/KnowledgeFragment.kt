package com.lh.wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.lh.wanandroid.R
import com.lh.wanandroid.adapter.HomeAdapter
import com.lh.wanandroid.adapter.KnowledgeAdapter
import com.lh.wanandroid.base.BaseMvpListFragment
import com.lh.wanandroid.constant.Constant
import com.lh.wanandroid.mvp.contract.KnowledgeContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.presenter.KnowledgePresenter
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 *@author: lh
 *CreateDate: 2020/8/3
 */
class KnowledgeFragment: BaseMvpListFragment<KnowledgeContract.View, KnowledgeContract.Presenter>(), KnowledgeContract.View {

    /** 公众号id **/
    private var cid: Int = 0

    private val data = ArrayList<Article>()

    private val adapter by lazy {
        KnowledgeAdapter(data)
    }

   companion object{
       fun newInstance(cid: Int) = KnowledgeFragment().apply {
           arguments = Bundle().apply {
               putInt(Constant.CONTENT_CID_KEY, cid)
           }
       }
   }

    override fun onRefreshList() {
        adapter.loadMoreModule.isEnableLoadMore = false
        mPresenter?.getWXArticles(cid, 0)
    }

    override fun createPresenter() = KnowledgePresenter()

    override fun initChildView(view: View) {
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: 0

        recyclerView.adapter = adapter
        adapter.loadMoreModule.setOnLoadMoreListener {
            isRefresh = false
            swipeRefreshLayout.isRefreshing = false
            val page = adapter.data.size / pageSize
            mPresenter?.getWXArticles(cid, page)
        }
    }

    override fun attachLayoutRes(): Int  = R.layout.fragment_knowledge

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getWXArticles(cid, 0)
    }

    override fun setWXArticles(articles: ArticleResponseBody) {
        articles.datas.let {
            adapter.run {
                if (isRefresh){
                    setList(it)
                    swipeRefreshLayout.isRefreshing = false
                }else
                    addData(it)

                if (articles.over)
                    loadMoreModule.loadMoreEnd(isRefresh)
                else
                    loadMoreModule.loadMoreComplete()
            }

            if (adapter.data.isEmpty())
                mLayoutStatusView?.showEmpty()
            else
                mLayoutStatusView?.showContent()
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
}