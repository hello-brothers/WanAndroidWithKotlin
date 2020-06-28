package com.lh.wanandroid.ui.activity

import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseActivity
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View{
    override fun attachLayoutRes() = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        initDrawableLayout()
    }

    override fun start() {
    }

    override fun createPresenter() = MainPresenter()

    override fun showLogoutSuccess(success: Boolean) {

    }

    private fun initDrawableLayout(){
        drawableLayout.run {
            val toggle = ActionBarDrawerToggle(
                this@MainActivity,
                this,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }
    }

}
