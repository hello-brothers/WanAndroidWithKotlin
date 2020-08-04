package com.lh.wanandroid.ui.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.SystemContract
import com.lh.wanandroid.mvp.presenter.SystemPresenter
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * 体系 包含SystemTreeFragment 和 NavigationFragment
 *
 *@author: lh
 *CreateDate: 2020/7/3
 */
class SystemFragment: BaseMvpFragment<SystemContract.View, SystemContract.Presenter>(), SystemContract.View {

    /** tab title **/
    private val titleList by lazy {
        ArrayList<String>().apply {
            add(getString(R.string.knowledge_system))
            add(getString(R.string.navigation))
        }
    }

    /** fragment List **/
    private val fragmentList by lazy {
        ArrayList<Fragment>().apply {
            add(SystemTreeFragment.newInstance())
            add(NavigationTreeFragment.newInstance())
        }
    }

    /** viewpager adapter **/
    private val pagerAdapter by lazy {
        SystemPagerAdapter(titleList, fragmentList, childFragmentManager)
    }

    companion object{
        fun getInstance() = SystemFragment()
    }

    override fun createPresenter(): SystemContract.Presenter  = SystemPresenter()

    override fun attachLayoutRes() = R.layout.fragment_system

    override fun lazyLoad() {

    }

    override fun initChildView(view: View) {
        viewPager.adapter = pagerAdapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.setupWithViewPager(viewPager)


    }

    override fun scrollToTop() {
        pagerAdapter.getItem(viewPager.currentItem).apply {
            if (this is SystemTreeFragment )
                scrollToTop()
            else if (this is NavigationTreeFragment)
                scrollToTop()
        }
    }


    private class SystemPagerAdapter(private val titleList: List<String>, private val fragmentList: List<Fragment>, fm: FragmentManager):
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getPageTitle(position: Int) = titleList[position]

        override fun getItem(position: Int) = fragmentList[position]

        override fun getCount() = fragmentList.size

    }
}