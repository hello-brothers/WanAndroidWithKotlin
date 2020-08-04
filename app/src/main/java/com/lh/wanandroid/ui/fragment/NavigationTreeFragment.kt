package com.lh.wanandroid.ui.fragment

import android.view.View
import android.widget.AbsListView.OnScrollListener.SCROLL_STATE_IDLE
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lh.wanandroid.R
import com.lh.wanandroid.adapter.NavigationAdapter
import com.lh.wanandroid.adapter.NavigationTabAdapter
import com.lh.wanandroid.base.BaseMvpFragment
import com.lh.wanandroid.mvp.contract.NavigationContract
import com.lh.wanandroid.mvp.model.bean.NavigationBean
import com.lh.wanandroid.mvp.presenter.NavigationPresenter
import kotlinx.android.synthetic.main.fragment_navigation.*
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 * 导航
 *@author: lh
 *CreateDate: 2020/8/4
 */
class NavigationTreeFragment: BaseMvpFragment<NavigationContract.View, NavigationContract.Presenter>(), NavigationContract.View {

    companion object{
        fun newInstance() = NavigationTreeFragment()
    }

    private val data = mutableListOf<NavigationBean>()

    private val navigationAdapter: NavigationAdapter by lazy {
        NavigationAdapter(data)
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity)
    }

    private var touchRecyclerView = false

    private var scrollByTouchRecyclerView = false

    private var lastFirstVisiblePosition = -1

    override fun createPresenter() = NavigationPresenter()

    override fun initChildView(view: View) {
        mLayoutStatusView = multiple_status_view

        recyclerView.run {
            layoutManager = linearLayoutManager
            adapter = navigationAdapter
            itemAnimator = DefaultItemAnimator()
            setHasFixedSize(true)
        }

        leftRightLink()

    }

    override fun attachLayoutRes() = R.layout.fragment_navigation

    override fun lazyLoad() {
        mLayoutStatusView?.showLoading()
        mPresenter?.getNavigationTree()
    }

    override fun setNavigation(navigationList: List<NavigationBean>) {

        navigationList.let {

            navigationTabLayout.setTabAdapter(NavigationTabAdapter(it, activity!!.applicationContext))

            navigationAdapter.setList(it)

            if (it.isEmpty()){
                mLayoutStatusView?.showEmpty()
                return@let
            }
            else
                mLayoutStatusView?.showContent()
        }
    }

    override fun scrollToTop() {
        navigationTabLayout.setTabSelected(0)
    }

    /** 左右滑动联动 **/
    private fun leftRightLink(){

        /** 左侧tabLayout点击监听 **/
        navigationTabLayout.addOnTabSelectedListener(object : VerticalTabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabView?, position: Int) {

            }

            override fun onTabSelected(tab: TabView?, position: Int) {
                /** 区分是手动点击还是右边recyclerview滑动引起的改变 **/
                if (!scrollByTouchRecyclerView)
                    selectTab(position)
            }

        })

        /** recyclerview touchListener **/
        recyclerView.setOnTouchListener { _, _ ->
            touchRecyclerView = true
            false
        }

        /** 右侧列表滑动监听 **/
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (touchRecyclerView && newState != SCROLL_STATE_IDLE)
                    scrollByTouchRecyclerView = true
                else if (newState == SCROLL_STATE_IDLE){
                    scrollByTouchRecyclerView = false
                    touchRecyclerView = false
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                /** 区分左边tabLayout点击引起的recyclerView滑动还是手动滑动 **/
                if (scrollByTouchRecyclerView){
                    val currentPosition =
                        linearLayoutManager.findFirstVisibleItemPosition()

                    if (currentPosition != lastFirstVisiblePosition)
                        navigationTabLayout.setTabSelected(currentPosition)
                    lastFirstVisiblePosition = currentPosition
                }

            }

        })
    }

    /** 左侧点击的tabLayout **/
    private fun selectTab(position: Int){
        recyclerView.stopScroll()
        smoothScrollToPosition(position)
    }

    private fun smoothScrollToPosition(currentPosition: Int){
        val firstPosition = linearLayoutManager.findFirstVisibleItemPosition()
        val lastPosition = linearLayoutManager.findLastVisibleItemPosition()

        /** 定位需要显示的item位置 1、小于当前界面显示的第一个item位置（界面上方） 2、位于当前界面内 3、大于当前界面显示的最后一个item位置（界面下方）**/
        when{
            currentPosition < firstPosition ->
                recyclerView.smoothScrollToPosition(currentPosition)

            currentPosition <= lastPosition -> {
                val top = recyclerView.getChildAt(currentPosition - firstPosition).top
                recyclerView.smoothScrollBy(0, top)
            }
            else ->
                recyclerView.smoothScrollToPosition(currentPosition)

        }
    }
}