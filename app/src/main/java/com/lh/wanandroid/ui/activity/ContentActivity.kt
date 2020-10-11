package com.lh.wanandroid.ui.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWebView
import com.just.agentweb.NestedScrollAgentWebView
import com.just.agentweb.WebChromeClient
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.constant.Constant
import com.lh.wanandroid.mvp.contract.ContentContract
import com.lh.wanandroid.mvp.presenter.ContentPresenter
import com.lh.wanandroid.widget.NestedScrolledAgentWebView
import kotlinx.android.synthetic.main.container.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Author: lh
 * Description:
 * Data: 2020/10/11
 */
class ContentActivity: BaseMvpActivity<ContentContract.View, ContentContract.Presenter>(), ContentContract.View {

    private lateinit var mAgentWeb: AgentWeb

    /** title **/
    private lateinit var mShareTitle: String
    /** url **/
    private lateinit var mShareUrl: String

    companion object{
        fun start(context: Context, title: String, url: String) {
            Intent(context, ContentActivity::class.java).run {
                putExtra(Constant.CONTENT_TITLE_KEY, title)
                putExtra(Constant.CONTEN_URL_KEY, url)
                context.startActivity(this)
            }
        }
    }

    override fun createPresenter(): ContentContract.Presenter = ContentPresenter()

    @SuppressLint("RestrictedApi")
    override fun initChildView() {

        toolbar.run {

            title = ""
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        toolbarTitle.apply {
            visibility = View.VISIBLE
            text = getString(R.string.agentweb_loading)
            isSelected = true

        }

        initWebView()
    }

    override fun attachLayoutRes() = R.layout.activity_content

    override fun initData() {
        mShareTitle = intent.extras?.getString(Constant.CONTENT_TITLE_KEY, "") ?: ""
        mShareUrl = intent.extras?.getString(Constant.CONTEN_URL_KEY, "") ?: ""

    }

    override fun start() {

    }

    private fun initWebView(){
        val webView = NestedScrolledAgentWebView(this)
        val layoutParams = CoordinatorLayout.LayoutParams(-1, -1)
        layoutParams.behavior = AppBarLayout.ScrollingViewBehavior()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(flContainer, layoutParams)
            .useDefaultIndicator()
            .setWebView(webView)
            .setWebChromeClient(mWebChromeClient)
            .createAgentWeb()
            .ready()
            .go(mShareUrl)
    }

    private val mWebChromeClient = object :WebChromeClient(){
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            toolbarTitle.text = title
        }
    }

    /** 返回上一页 **/
    override fun onBackPressed() {
        if (!mAgentWeb.back())
            super.onBackPressed()
    }
}