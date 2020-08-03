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
        if (isRefresh){
            adapter.setList(knowledgeList)
            swipeRefreshLayout.isRefreshing = false
        }
        else
            adapter.addData(knowledgeList)

        if (adapter.data.isEmpty())
            mLayoutStatusView?.showEmpty()
        else
            mLayoutStatusView?.showContent()
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