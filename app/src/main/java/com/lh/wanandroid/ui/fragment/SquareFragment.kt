package com.lh.wanandroid.ui.fragment

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.adapter.HomeAdapter
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpListFragment
import com.lh.wanandroid.ext.shortToast
import com.lh.wanandroid.mvp.contract.SquareContract
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.presenter.SquarePresenter
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 * 广场
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SquareFragment: BaseMvpListFragment<SquareContract.View, SquareContract.Presenter, Article>(), SquareContract.View {

    companion object{
        fun getInstance(): SquareFragment = SquareFragment()
    }

    private val data = ArrayList<Article>()

    override fun createPresenter(): SquareContract.Presenter  = SquarePresenter()

    override fun attachLayoutRes() = R.layout.fragment_square

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.requestSquareList(0)
    }

    override fun initChildView(view: View) {

        /** 不设置 该fragment不接受onCreateOptionsMenu的调用 **/
        setHasOptionsMenu(true)

    }

    override fun setSquareList(article: ArticleResponseBody) {

        article.datas.let {
            rvAdapter.run {
                if (isRefresh){
                    setList(it)
                    swipeRefreshLayout.isRefreshing = false
                }
                else
                    addData(it)

                if (article.over)
                    loadMoreModule.loadMoreEnd(isRefresh)
                else
                    loadMoreModule.loadMoreComplete()
            }
        }

        if (rvAdapter.data.isEmpty())
            mLayoutStatusView?.showEmpty()
        else
            mLayoutStatusView?.showContent()
    }

    override fun scrollToTop() {

        recyclerView.run {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20)
                scrollToPosition(0)
            else
                smoothScrollToPosition(0)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater?.inflate(R.menu.menu_fragment_square, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_add -> getString(R.string.action_add).shortToast()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefreshList() {
        mPresenter?.requestSquareList(0)
    }

    override fun onLoadMore() {
        (rvAdapter.data.size / pageSize).run{
            mPresenter?.requestSquareList(this)
        }

    }

    override fun generateAdapter() = HomeAdapter(data)
}