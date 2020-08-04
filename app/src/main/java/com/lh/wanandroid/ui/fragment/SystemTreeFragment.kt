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
class SystemTreeFragment: BaseMvpListFragment<SystemTreeContract.View, SystemTreeContract.Presenter>(), SystemTreeContract.View {


    companion object{
        fun newInstance() = SystemTreeFragment()
    }
    private val data = arrayListOf<KnowledgeTreeBody>()
    private val adapter by lazy {
        SystemTreeAdapter(data)
    }
    override fun onRefreshList() {
        mPresenter?.getSystemTree()

    }

    override fun createPresenter() = SystemTreePresenter()

    override fun initChildView(view: View) {

        recyclerView.adapter = adapter
    }

    override fun attachLayoutRes() = R.layout.fragment_systemtree

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getSystemTree()
    }

    override fun setSystemTree(knowledgeList: List<KnowledgeTreeBody>) {

        adapter.setList(knowledgeList)
        if (isRefresh)
            swipeRefreshLayout.isRefreshing = false


        if (adapter.data.isEmpty())
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

    private class SystemTreeAdapter(data: MutableList<KnowledgeTreeBody>) : BaseQuickAdapter<KnowledgeTreeBody, BaseViewHolder>(R.layout.item_systemtree_list, data){
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