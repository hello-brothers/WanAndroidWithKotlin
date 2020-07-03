package com.lh.wanandroid.ui.activity

import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseActivity
import com.lh.wanandroid.base.BaseFragment
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.presenter.MainPresenter
import com.lh.wanandroid.ui.fragment.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseMvpActivity<MainContract.View, MainContract.Presenter>(), MainContract.View{

    companion object{
        private const val FRAGMENT_HOME         = 0x01;
        private const val FRAGMENT_SQUARE       = 0x02;
        private const val FRAGMENT_WECHAT       = 0x03;
        private const val FRAGMENT_SYSTEM       = 0x04;
        private const val FRAGMENT_PROJECT      = 0x05;

    }

    private var mHomeFragment: HomeFragment?            = null
    private var mProjectFragment: ProjectFragment?      = null
    private var mSquareFragment: SquareFragment?        = null
    private var mSystemFragment: SystemFragment?        = null
    private var mWechatFragment: WechatFragment?        = null


    /** 当前界面显示fragment位置索引 **/
    private var mIndex = FRAGMENT_HOME

    override fun attachLayoutRes() = R.layout.activity_main

    override fun initData() {
    }

    override fun initView() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        showFragment(mIndex)
        initBottomNavigation()
        initDrawableLayout()
    }

    override fun start() {
    }

    override fun createPresenter() = MainPresenter()

    override fun showLogoutSuccess(success: Boolean) {

    }

    private fun initBottomNavigation(){
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener)
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

    private val onNavigationItemReselectedListener = BottomNavigationView.OnNavigationItemSelectedListener{
        item: MenuItem ->
        return@OnNavigationItemSelectedListener when(item.itemId){
            R.id.action_home -> {
                showFragment(FRAGMENT_HOME)
                true
            }
            R.id.action_square -> {
                showFragment(FRAGMENT_SQUARE)
                true
            }
            R.id.action_wechat -> {
                showFragment(FRAGMENT_WECHAT)
                true
            }
            R.id.action_system -> {
                showFragment(FRAGMENT_SYSTEM)
                true
            }
            R.id.action_project -> {
                showFragment(FRAGMENT_PROJECT)
                true
            }
            else -> false
        }
    }


    private fun showFragment(index: Int){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        hideFragment(fragmentTransaction)

        mIndex = index

        when(index){
            FRAGMENT_HOME ->{
                toolbar.title = getString(R.string.app_name)
                if (mHomeFragment == null){
                    mHomeFragment = HomeFragment.getInstance()
                    fragmentTransaction.add(R.id.flContainer, mHomeFragment!!, "home")
                }else{
                    fragmentTransaction.show(mHomeFragment!!)
                }
            }
            FRAGMENT_SQUARE ->{
                toolbar.title = getString(R.string.square)
                if (mSquareFragment == null){
                    mSquareFragment = SquareFragment.getInstance()
                    fragmentTransaction.add(R.id.flContainer, mSquareFragment!!, "square")
                }else{
                    fragmentTransaction.show(mSquareFragment!!)
                }
            }
            FRAGMENT_WECHAT ->{
                toolbar.title = getString(R.string.wechat)
                if (mWechatFragment == null){
                    mWechatFragment = WechatFragment.getInstance()
                    fragmentTransaction.add(R.id.flContainer, mWechatFragment!!, "wechat")
                }else{
                    fragmentTransaction.show(mWechatFragment!!)
                }
            }
            FRAGMENT_SYSTEM ->{
                toolbar.title = getString(R.string.knowledge_system)
                if (mSystemFragment == null){
                    mSystemFragment = SystemFragment.getInstance()
                    fragmentTransaction.add(R.id.flContainer, mSystemFragment!!, "wechat")
                }else{
                    fragmentTransaction.show(mSystemFragment!!)
                }
            }

            FRAGMENT_PROJECT ->{
                toolbar.title = getString(R.string.project)
                if (mProjectFragment == null){
                    mProjectFragment = ProjectFragment.getInstance()
                    fragmentTransaction.add(R.id.flContainer, mProjectFragment!!, "wechat")
                }else{
                    fragmentTransaction.show(mProjectFragment!!)
                }
            }
        }
        fragmentTransaction.commit()

    }

    private fun hideFragment(transaction: FragmentTransaction){

        mHomeFragment?.hide(transaction)
        mProjectFragment?.hide(transaction)
        mSquareFragment?.hide(transaction)
        mSystemFragment?.hide(transaction)
        mWechatFragment?.hide(transaction)

    }


    private fun BaseFragment.hide(transaction: FragmentTransaction){
        transaction.hide(this)
    }



}




