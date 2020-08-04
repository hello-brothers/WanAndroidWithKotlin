package com.lh.wanandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.R
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.NavigationBean
import com.lh.wanandroid.utils.CommonUtil
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import org.jetbrains.anko.textColor

/**
 *@author: lh
 *CreateDate: 2020/8/4
 */
class NavigationAdapter( data: MutableList<NavigationBean>?) :
    BaseQuickAdapter<NavigationBean, BaseViewHolder>(R.layout.item_navigation_list, data) {

    override fun convert(holder: BaseViewHolder, item: NavigationBean) {
        holder.setText(R.id.itemNavigationTitle, item.name)

        val flowLayout = holder.getView<TagFlowLayout>(R.id.itemNavigationFlowLayout) as TagFlowLayout
        flowLayout.run {

            adapter =object : TagAdapter<Article>(item.articles){
                override fun getView(parent: FlowLayout?, position: Int, t: Article?): View? {
                    return t?.run {
                        return LayoutInflater.from(parent!!.context)
                            .inflate(R.layout.flow_layout_tv, flowLayout, false).apply {
                                this as TextView
                                text = t.title
                                textColor = CommonUtil.randomColor()
                        }
                    } ?: null
                }
            }

            setOnTagClickListener { view, position, parent ->
                Toast.makeText(parent.context!!, item.articles[position].title, Toast.LENGTH_SHORT).show()
                true
            }

        }


    }
}