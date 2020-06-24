package com.lh.wanandroid.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.lh.wanandroid.constant.Constant
import com.lh.wanandroid.event.NetworkChangeEvent
import com.lh.wanandroid.utils.Preference
import com.lh.wanandroid.utils.isNetworkConnected
import org.greenrobot.eventbus.EventBus

class NetworkChangeReceiver : BroadcastReceiver() {


    private var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    override fun onReceive(context: Context, intent: Intent) {

        val isConnected = isNetworkConnected(context)
        if (isConnected){
            //post网络可用
            if (isConnected != hasNetwork){
                EventBus.getDefault().post(NetworkChangeEvent(isConnected))
            }
        }else{
            //post网络不可用
            EventBus.getDefault().post(NetworkChangeEvent(isConnected))
        }

    }
}
