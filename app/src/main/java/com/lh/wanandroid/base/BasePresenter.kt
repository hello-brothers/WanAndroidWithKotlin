package com.lh.wanandroid.base

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.lh.wanandroid.base.mvp.IModel
import com.lh.wanandroid.base.mvp.IPresenter
import com.lh.wanandroid.base.mvp.IView
import org.greenrobot.eventbus.EventBus

/**
 *@author: lh
 *CreateDate: 2020/6/28
 */
abstract class BasePresenter<M: IModel, V: IView>: IPresenter<V>, LifecycleObserver {

    protected var mModel: M?     = null
    protected var mView: V?      = null

    private val isiViewAttached: Boolean
        get() = mView != null


    /** 创建Model **/
    abstract fun createModel(): M?

    /** 是否使用EventBus **/
    open fun useEventBus(): Boolean = false

    override fun attachView(mView: V) {
        this.mView = mView
        mModel = createModel()
        if (mView is LifecycleObserver){
            (mView as LifecycleOwner).lifecycle.addObserver(this)
            if (mModel != null && mModel is LifecycleObserver){
                (mView as LifecycleOwner).lifecycle.addObserver(mModel as LifecycleObserver)
            }
        }
        if (useEventBus()){
            EventBus.getDefault().register(this)
        }
    }

    override fun detachView() {
        if (useEventBus()){
            EventBus.getDefault().unregister(this)
        }

        mModel?.onDetach()
        this.mModel = null
        this.mView = null

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(owner: LifecycleOwner) {
        // detachView()
        owner.lifecycle.removeObserver(this)
    }
}