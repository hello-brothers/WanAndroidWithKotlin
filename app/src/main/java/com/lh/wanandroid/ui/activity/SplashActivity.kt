package com.lh.wanandroid.ui.activity

import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.lh.wanandroid.R
import com.lh.wanandroid.base.BaseActivity
import com.lh.wanandroid.utils.cStartActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    private lateinit var alphaAnimation: AlphaAnimation

    override fun attachLayoutRes() = R.layout.activity_splash

    /** 不注册EventBus **/
    override fun userEventBus() = false

    /** 闪屏界面是现实tip提示 **/
    override fun enableNetworkTip() = false

    override fun initData() {

    }

    override fun initColor() {
        super.initColor()
        layout_splash.setBackgroundColor(mThemeColor)
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation.run {
            duration = 2000
            setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationRepeat(animation: Animation?) {
                }

                override fun onAnimationEnd(animation: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
        }
        layout_splash.startAnimation(alphaAnimation)
    }

    override fun start() {
    }

    private fun jumpToMain(){
        cStartActivity<MainActivity>(this)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }



}
