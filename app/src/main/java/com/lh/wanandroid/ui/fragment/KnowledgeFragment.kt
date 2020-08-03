package com.lh.wanandroid.ui.fragment

import android.os.Bundle
import android.view.View
import com.lh.wanandroid.R
import com.lh.wanandroid.adapter.HomeAdapter
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


    private var cid: Int = 0

    private val data = ArrayList<Article>()
    private val adapter by lazy {
        HomeAdapter(data)
    }
   companion object{
       fun newInstance(cid: Int) = KnowledgeFragment().apply {
           arguments = Bundle().apply {
               putInt(Constant.CONTENT_CID_KEY, cid)
           }
       }
   }

    override fun onRefreshList() {

    }

    override fun createPresenter() = KnowledgePresenter()

    override fun initChildView(view: View) {
        cid = arguments?.getInt(Constant.CONTENT_CID_KEY) ?: 0
    }

    override fun attachLayoutRes(): Int  = R.layout.fragment_knowledge

    override fun lazyLoad() {
        mPresenter?.getWXArticles(cid, 0)
    }

    override fun setWXArticles(articles: ArticleResponseBody) {
        articles.datas.let {
            adapter.run {
                if (isRefresh){
                    swipeRefreshLayout.isRefreshing = false
                    setList(it)
                }else
                    addData(it)
            }

            if (adapter.data.isEmpty())
                mLayoutStatusView?.showEmpty()
            else
                mLayoutStatusView?.showContent()
        }
    }
}