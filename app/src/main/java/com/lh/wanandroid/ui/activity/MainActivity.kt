package com.lh.wanandroid.ui.activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseFragment
import com.lh.wanandroid.base.BaseMvpActivity
import com.lh.wanandroid.base.mvp.IView
import com.lh.wanandroid.event.LoginEvent
import com.lh.wanandroid.ext.shortToast
import com.lh.wanandroid.mvp.contract.MainContract
import com.lh.wanandroid.mvp.contract.fcinterface.IScrollToTop
import com.lh.wanandroid.mvp.model.bean.UserInfoBody
import com.lh.wanandroid.mvp.presenter.MainPresenter
import com.lh.wanandroid.ui.fragment.*
import com.lh.wanandroid.utils.DayNightModeUtil
import com.lh.wanandroid.utils.Preference
import com.lh.wanandroid.utils.SettingUtil
import com.lh.wanandroid.utils.cStartActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

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

    private lateinit var tvUserName: TextView
    private lateinit var tvUserRank: TextView
    private lateinit var tvUserGrade: TextView
    //侧边栏我的积分
    private lateinit var navScore: TextView

    /** 当前界面显示fragment位置索引 **/
    private var mIndex = FRAGMENT_HOME

    private val BOTTOMINDEX = "bottomIndex"
    override fun onCreate(savedInstanceState: Bundle?) {
        mIndex = savedInstanceState?.getInt(BOTTOMINDEX)?:mIndex
        super.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BOTTOMINDEX, mIndex)
    }

    override fun attachLayoutRes() = R.layout.activity_main

    override fun initColor() {
        super.initColor()
        floating_action_btn.backgroundColor = mThemeColor
        floating_action_btn.backgroundTintList = ColorStateList.valueOf(mThemeColor)
    }

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
        if (isLogin)
            mPresenter?.getUserInfo()
    }

    override fun createPresenter() = MainPresenter()

    override fun showLogoutSuccess(success: Boolean) {
        if (success){
            doAsync {
                Preference.clearPreference()
                uiThread {
                    getString(R.string.logout_success).shortToast()
                    mUserName = tvUserName.text.trim().toString()
                    isLogin = false
                    EventBus.getDefault().post(LoginEvent(false))
                }
            }
        }

    }

    override fun showUserInfo(userInfo: UserInfoBody) {
        tvUserRank.text = userInfo.rank.toString()
        tvUserGrade.text = (userInfo.coinCount/100+1).toString()

        navScore.text = userInfo.coinCount.toString()
    }

    private fun initBottomNavigation(){
        bottom_navigation.setOnNavigationItemSelectedListener(onNavigationItemReselectedListener)
    }

    private fun initLeftNavigation(){
        navigationView.run {
            getHeaderView(0).run {
                tvUserName = findViewById(R.id.userName)
                tvUserGrade = findViewById(R.id.tvUserGrade)
                tvUserRank = findViewById(R.id.tvUserRank)

                navScore = (navigationView.menu.findItem(R.id.navScore).actionView as TextView).apply {
                    gravity = Gravity.CENTER_VERTICAL
                }

                findViewById<ImageView>(R.id.ivRank).setOnClickListener(leftNavigationHeadClickListener)
                findViewById<TextView>(R.id.userName).setOnClickListener(leftNavigationHeadClickListener)
            }
            tvUserName.text = if (isLogin) mUserName else getString(R.string.go_login)
            menu.findItem(R.id.navLogout).isVisible = isLogin
            setNavigationItemSelectedListener(leftNavigationMenuClickListener)
        }
    }

    /** FloatingActionButton **/
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


    /** 用户list的fragment滑动到顶部 **/
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

    /** 返回当前fragment用于刷新界面数据 **/
    private fun getCurrentViewByIndex(index: Int): IView? {
        return when(index){
            FRAGMENT_HOME ->  mHomeFragment
            FRAGMENT_PROJECT -> mProjectFragment
            FRAGMENT_SYSTEM -> mSystemFragment
            FRAGMENT_WECHAT -> mWechatFragment
            FRAGMENT_SQUARE -> mSquareFragment
            else -> null
        }
    }

    /** Menu **/
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

            R.id.navLogout ->{
                logout()
            }

            //切换黑白主题
            R.id.navNightMode ->{
                DayNightModeUtil.setNightModeEnable(!SettingUtil.getNightModeStatus())

                window.setWindowAnimations(R.style.WindowAnimationFadeInAndOut)
                recreate()
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

    private fun logout(){
        mPresenter?.logout()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun loginEvent(event: LoginEvent){
        if (event.isLogin){
            tvUserName.text = mUserName
            navigationView.menu.findItem(R.id.navLogout).isVisible = true
            mPresenter?.getUserInfo()
        }else{
            tvUserName.text = getString(R.string.go_login)
            navigationView.menu.findItem(R.id.navLogout).isVisible = false
            tvUserGrade.text = getString(R.string.nav_line_2)
            tvUserRank.text = getString(R.string.nav_line_2)
        }

        getCurrentViewByIndex(mIndex)?.autoRefresh()
    }


}




