package com.lh.wanandroid.ui.fragment

import android.text.Html
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpListFragment
import com.lh.wanandroid.mvp.contract.SystemTreeContract
import com.lh.wanandroid.mvp.model.bean.KnowledgeTreeBody
import com.lh.wanandroid.mvp.presenter.SystemTreePresenter
import kotlinx.android.synthetic.main.refresh_layout.*

/**
 * Author: lh
 * Description:
 * Data: 2020/8/3
 */
class SystemTreeFragment: BaseMvpListFragment<SystemTreeContract.View, SystemTreeContract.Presenter, KnowledgeTreeBody>(), SystemTreeContract.View {

    companion object{
        fun newInstance() = SystemTreeFragment()
    }

    override fun onRefreshList() {
        mPresenter?.getSystemTree()

    }

    override fun createPresenter() = SystemTreePresenter()

    override fun initChildView(view: View) {

    }

    override fun attachLayoutRes() = R.layout.fragment_systemtree

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getSystemTree()
    }

    override fun setSystemTree(knowledgeList: List<KnowledgeTreeBody>) {

        rvAdapter.setList(knowledgeList)
        if (isRefresh)
            swipeRefreshLayout.isRefreshing = false


        if (rvAdapter.data.isEmpty())
            mLayoutStatusView?.showEmpty()
        else
            mLayoutStatusView?.showContent()
    }

    override fun scrollToTop() {
        recyclerView.apply {
            if (linearLayoutManager.findFirstVisibleItemPosition() > 20)
                scrollToPosition(0)
            else
                smoothScrollToPosition(0)
        }
    }

    override fun onLoadMore() {

    }

    override val isLoadMore: Boolean
        get() = false

    override fun generateAdapter() = SystemTreeAdapter(arrayListOf())

    class SystemTreeAdapter(data: MutableList<KnowledgeTreeBody>) : BaseQuickAdapter<KnowledgeTreeBody, BaseViewHolder>(R.layout.item_systemtree_list, data){
        override fun convert(holder: BaseViewHolder, item: KnowledgeTreeBody) {
            holder.setText(R.id.title_first, item.name)

            item.children.let { it ->
                holder.setText(R.id.title_second, it.joinToString("    ", transform = {
                    Html.fromHtml(it.name)
                }))
            }
        }

    }

}