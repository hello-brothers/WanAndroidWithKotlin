package com.lh.wanandroid.base

import android.content.Context
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import com.lh.wanandroid.R
import com.lh.wanandroid.app.App
import com.lh.wanandroid.constant.Constant
import com.lh.wanandroid.event.NetworkChangeEvent
import com.lh.wanandroid.receiver.NetworkChangeReceiver
import com.lh.wanandroid.utils.Preference
import com.lh.wanandroid.utils.SettingUtil
import com.lh.wanandroid.utils.StatusBarUtil
import kotlinx.android.synthetic.main.toolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *@author: lh
 *CreateDate: 2020/6/23
 */
abstract class BaseActivity: AppCompatActivity() {


    /** loginStatus **/
    protected var isLogin by Preference(Constant.LOGIN_KEY, false)
    /** userName **/
    protected var mUserName by Preference(Constant.USERNAME_KEY, "")
    /** password **/
    protected var mPassword by Preference(Constant.PASSWORD_KEY, "")
    /** token **/
    protected var mToken by Preference(Constant.TOKEN_KEY, "")
    /** 主题色 **/
    protected var mThemeColor: Int =  App.context.resources.getColor(R.color.colorPrimary)
    protected var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    /** 提示View **/
    private lateinit var mLayoutParams: WindowManager.LayoutParams
    private lateinit var mWindowManager: WindowManager
    private lateinit var mTipView: View

    /** 网络状态变化的广播 **/
    private var mNetworkChangeReceiver: NetworkChangeReceiver? = null

    /** 布局文件id **/
    protected abstract fun attachLayoutRes(): Int

    /** 初始化数据 **/
    abstract fun initData()

    /** 初始化View **/
    abstract fun initView()

    /** 开始请求 **/
    abstract fun start()

    /** 是否使用EventBus **/
    open fun userEventBus(): Boolean = true

    /** 是否需要显示TipView **/
    open fun enableNetworkTip(): Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        /** 输入框不希望遮挡设置activity属性 **/
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        setContentView(attachLayoutRes())

        if (userEventBus()){
            EventBus.getDefault().register(this)
        }

        initData()
        initTipView()
        initView()
        start()
    }

    override fun onResume() {
        //动态注册网络变化广播
        val filter = IntentFilter()
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        mNetworkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(mNetworkChangeReceiver, filter)
        super.onResume()
        initColor()
    }

    override fun onPause() {
        if (mNetworkChangeReceiver != null){
            unregisterReceiver(mNetworkChangeReceiver)
            mNetworkChangeReceiver = null
        }
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (userEventBus()){
            EventBus.getDefault().unregister(this)
        }
    }

    override fun finish() {
        super.finish()
        if (mTipView != null && mTipView.parent != null){
            mWindowManager.removeView(mTipView)
        }
    }

    /** 关于颜色方面的初始化 **/
    open fun initColor(){
        mThemeColor = if (SettingUtil.getNightModeStatus()){
            resources.getColor(R.color.colorPrimary)
        }else{
            SettingUtil.getColor()
        }
        StatusBarUtil.setColor(this, mThemeColor, 0)
    }


    /** 初始化TipView **/
    private fun initTipView(){
        mTipView = layoutInflater.inflate(R.layout.layout_network_tip, null)
        mWindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        mLayoutParams = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            PixelFormat.TRANSLUCENT
        )
        mLayoutParams.gravity = Gravity.TOP
        mLayoutParams.x = 0
        mLayoutParams.y = 0
        mLayoutParams.windowAnimations = R.style.anim_float_view
    }

    //设置toolbar
    protected fun initToolbar(toolbar: Toolbar, initTitle: String, showHomeAsUp: Boolean){
        toolbar?.run {
            //TODO 设置标题必须要在setSupportActionBar()方法之前才有效
            title = initTitle
            setSupportActionBar(this)
            supportActionBar?.setDisplayHomeAsUpEnabled(showHomeAsUp)
        }
    }
    /** EventBus监听网络变化时间 **/
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNetWorkChangeEvent(event: NetworkChangeEvent){
        hasNetwork = event.isConnected
        checkNetwork(event.isConnected)
    }

    private fun checkNetwork(isConnected: Boolean){
        if (enableNetworkTip()){
            if (isConnected){

                if (mTipView != null && mTipView.parent != null){
                    mWindowManager.removeView(mTipView)
                }
            }else{
                if (mTipView.parent == null){
                    mWindowManager.addView(mTipView, mLayoutParams)
                }
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            /** 设置toolbar的返回按钮，点击出栈 **/
            android.R.id.home ->{
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //fragment逐个出栈
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0)
            super.onBackPressed()
        else
            supportFragmentManager.popBackStack()
    }
}