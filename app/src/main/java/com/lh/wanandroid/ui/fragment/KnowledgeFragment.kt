package com.lh.wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
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
class KnowledgeFragment: BaseMvpListFragment<KnowledgeContract.View, KnowledgeContract.Presenter, Article>(), KnowledgeContract.View {

    /** 公众号id **/
    private var cid: Int = 0

    private val data = ArrayList<Article>()

   companion object{
       fun newInstance(cid: Int) = KnowledgeFragment().apply {
           arguments = Bundle().apply {
               putInt(Constant.CONTENT_CID_KEY, cid)
           }
       }
   }
    override fun onRefreshList() {
        mPresenter?.getWXArticles(cid, 0)
    }

    override fun createPresenter() = KnowledgePresenter()

    override fun initChildView(view: View) {
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: 0

    }

    override fun attachLayoutRes(): Int  = R.layout.fragment_knowledge

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getWXArticles(cid, 0)
    }

    override fun setWXArticles(articles: ArticleResponseBody) {
        articles.datas.let {
            rvAdapter.run {
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

            if (rvAdapter.data.isEmpty())
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

    override fun onLoadMore() {
        (rvAdapter.data.size / pageSize).run {
            mPresenter?.getWXArticles(cid, this)
        }
    }

    override fun generateAdapter() = KnowledgeAdapter(data)
}