package com.lh.wanandroid.ui.fragment

import android.view.View
import com.google.android.material.tabs.TabLayout
import com.lh.wanandroid.R
import com.lh.wanandroid.adapter.WeChatPageAdapter
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.WechatContract
import com.lh.wanandroid.mvp.model.bean.WXChapterBean
import com.lh.wanandroid.mvp.presenter.WechatPresenter
import com.lh.wanandroid.utils.shortToast
import kotlinx.android.synthetic.main.fragment_wechat.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class WechatFragment: BaseMvpFragment<WechatContract.View, WechatContract.Presenter>(), WechatContract.View {

    companion object{
        fun getInstance() = WechatFragment()
    }


    private val data = mutableListOf<WXChapterBean>()

    private val pagerAdapter by lazy {
        WeChatPageAdapter(data, childFragmentManager)
    }

    override fun createPresenter(): WechatContract.Presenter  = WechatPresenter()

    override fun attachLayoutRes() = R.layout.fragment_wechat

    override fun lazyLoad() {

        mLayoutStatusView?.showLoading()
        mPresenter?.requestWXChapters()

    }

    override fun initChildView(view: View) {

        mLayoutStatusView = multiple_status_view
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))

        tabLayout.setupWithViewPager(viewPager)

    }

    override fun setWXChapters(chapters: List<WXChapterBean>) {

        chapters.let {
            data.addAll(chapters)
            doAsync {
                Thread.sleep(10)
                uiThread {
                    viewPager.run {
                        adapter = pagerAdapter
                        offscreenPageLimit = data.size
                    }
                }
            }
        }


        if (chapters.isEmpty())
            mLayoutStatusView?.showEmpty()
        else
            mLayoutStatusView?.showContent()
    }

    override fun scrollToTop() {
        pagerAdapter.getItem(viewPager.currentItem).run {
            if (this is KnowledgeFragment){
                this.scrollToTop()
            }
        }
    }
}