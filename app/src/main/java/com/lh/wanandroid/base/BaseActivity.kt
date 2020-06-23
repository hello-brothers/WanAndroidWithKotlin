package com.lh.wanandroid.base

import android.content.Context
import android.content.IntentFilter
import android.graphics.PixelFormat
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.lh.wanandroid.R
import com.lh.wanandroid.event.NetworkChangeEvent
import com.lh.wanandroid.receiver.NetworkChangeReceiver
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *@author: lh
 *CreateDate: 2020/6/23
 */
abstract class BaseActivity: AppCompatActivity() {

    private lateinit var mLayoutParams: WindowManager.LayoutParams
    private lateinit var mWindowManager: WindowManager
    private lateinit var mTipView: View
    /** 网络状态变化的广播 **/
    private lateinit var mNetworkChangeReceiver: NetworkChangeReceiver

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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNetWorkChangeEvent(event: NetworkChangeEvent){
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
}