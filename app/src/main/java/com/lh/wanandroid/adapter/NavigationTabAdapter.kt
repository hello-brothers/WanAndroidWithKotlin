package com.lh.wanandroid.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import com.lh.wanandroid.R
import com.lh.wanandroid.mvp.model.bean.NavigationBean
import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

/**
 *@author: lh
 *CreateDate: 2020/8/4
 */
class NavigationTabAdapter(private val navigationList: List<NavigationBean>, val context: Context): TabAdapter {
    override fun getIcon(position: Int) = null

    override fun getBadge(position: Int) = null

    override fun getBackground(position: Int) = -1

    override fun getTitle(position: Int)
            = ITabView.TabTitle.Builder()
        .setContent(navigationList[position].name)
        .setTextColor(ContextCompat.getColor(context, R.color.colorAccent), ContextCompat.getColor(context, R.color.Grey500)).build()

    override fun getCount() = navigationList.size
}