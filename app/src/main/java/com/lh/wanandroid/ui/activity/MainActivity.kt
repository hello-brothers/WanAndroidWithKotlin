package com.lh.wanandroid.ui.activity

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseFragment
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.base.BaseMvpListFragment
import com.lh.wanandroid.ext.shortToast
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop
import com.lh.wanandroid.mvp.presenter.MainPresenter
import com.lh.wanandroid.ui.fragment.*
import com.lh.wanandroid.utils.cStartActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

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

    override fun initChildView() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }

        showFragment(mIndex)

        initLeftNavigation()
        initBottomNavigation()

        initDrawableLayout()
        initFloatActionBtn()
    }


    override fun start() {
    }

    override fun createPresenter() = MainPresenter()

    override fun showLogoutSuccess(success: Boolean) {

    }

    private fun initBottomNavigation(){
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener)
    }

    private fun initLeftNavigation(){
        navigationView.run {
            getHeaderView(0).run {

                findViewById<ImageView>(R.id.ivRank).setOnClickListener(leftNavigationHeadClickListener)
                findViewById<TextView>(R.id.userName).setOnClickListener(leftNavigationHeadClickListener)
            }
            setNavigationItemSelectedListener(leftNavigationMenuClickListener)
        }
    }

    private fun initFloatActionBtn(){
        floating_action_btn.setOnClickListener {
            getCurrentFragmentByIndex(mIndex)?.let {
                it.scrollToTop()
            }
        }
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
                    mHomeFragment = HomeFragment.newInstance()
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
                    fragmentTransaction.add(R.id.flContainer, mSystemFragment!!, "system")
                }else{
                    fragmentTransaction.show(mSystemFragment!!)
                }
            }

            FRAGMENT_PROJECT ->{
                toolbar.title = getString(R.string.project)
                if (mProjectFragment == null){
                    mProjectFragment = ProjectFragment.getInstance()
                    fragmentTransaction.add(R.id.flContainer, mProjectFragment!!, "project")
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


    private fun getCurrentFragmentByIndex(index: Int): IScrollToTop? {
        return when(index){
            FRAGMENT_HOME ->  mHomeFragment
            FRAGMENT_PROJECT -> mProjectFragment
            FRAGMENT_SYSTEM -> mSystemFragment
            FRAGMENT_WECHAT -> mWechatFragment
            FRAGMENT_SQUARE -> mSquareFragment
            else -> null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (mIndex != FRAGMENT_SQUARE)
            menuInflater.inflate(R.menu.menu_activity_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.action_search -> getString(R.string.action_search).shortToast()
        }
        return super.onOptionsItemSelected(item)
    }


    /** left navigation Menu Listener **/
    private val leftNavigationMenuClickListener = NavigationView.OnNavigationItemSelectedListener{
        when(it.itemId){
            //我的积分
            R.id.navScore ->{
                it.title.toString().shortToast()
            }
            R.id.navCollect ->{
                it.title.toString().shortToast()
            }

        }

        true
    }

    private val leftNavigationHeadClickListener = View.OnClickListener {
        when(it.id){
            R.id.ivRank ->{
                cStartActivity<LoginActivity>(this)
            }
            R.id.userName ->{
                cStartActivity<LoginActivity>(this)
            }
        }
    }

}




