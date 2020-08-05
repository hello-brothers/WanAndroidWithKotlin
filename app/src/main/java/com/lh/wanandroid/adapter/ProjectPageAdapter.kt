package com.lh.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import com.lh.wanandroid.ui.fragment.ProjectListFragment

/**
 *@author: lh
 *CreateDate: 2020/8/5
 */
class ProjectPageAdapter(fm: FragmentManager, private  val dataList: List<WXChapterBean>) :
    FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()
    init {
        dataList.forEach {
            fragments.add(ProjectListFragment.newInstance(it.id))
        }
    }
    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = dataList.size

    override fun getPageTitle(position: Int): CharSequence = dataList[position].name
}