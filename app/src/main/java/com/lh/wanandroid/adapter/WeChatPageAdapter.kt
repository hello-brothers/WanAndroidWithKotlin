package com.lh.wanandroid.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import com.lh.wanandroid.ui.fragment.KnowledgeFragment

/**
 *@author: lh
 *CreateDate: 2020/7/24
 */
class WeChatPageAdapter(private val list: MutableList<WXChapterBean>, fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragments = mutableListOf<Fragment>()
    init {
        list.forEach {
            fragments.add(KnowledgeFragment.newInstance(it.id))
        }
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = list.size

    override fun getPageTitle(position: Int): CharSequence?  = list[position].name

    override fun getItemPosition(`object`: Any) =PagerAdapter.POSITION_NONE

}