package com.lh.wanandroid.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lh.wanandroid.event.NetworkChangeEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
abstract class BaseFragment: Fragment() {

    /** 视图是否加载完毕 **/
    private var isViewPrepare = false

    /** 数据是否加载过了 **/
    private var hasLoadData = false




    /** 加载布局 **/
    abstract fun attachLayoutRes(): Int

    /** 初始化View **/
    abstract fun initView(view: View)

    /** 懒加载 **/
    abstract fun lazyLoad()

    /** 是否使用EventBus **/
    open fun useEventBus(): Boolean = true

    /** 重新请求 无网状态->有网状态的自动重连操作，子类可重写**/
    open fun doReConnected(){
        lazyLoad()
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater?.inflate(attachLayoutRes(), null)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser){
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (useEventBus()){
            EventBus.getDefault().register(this)
        }
        isViewPrepare = true
        //开始加载视图
        initView(view)
        //开始请求数据
        lazyLoadDataIfPrepared()

    }


    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData){
            lazyLoad()
            hasLoadData = true
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNetworkChangeEvent(event: NetworkChangeEvent){
        if (event.isConnected){
            doReConnected()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()){
            EventBus.getDefault().unregister(this)
        }
    }

}