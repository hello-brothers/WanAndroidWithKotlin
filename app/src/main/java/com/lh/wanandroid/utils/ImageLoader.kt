package com.lh.wanandroid.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.lh.wanandroid.R
import com.lh.wanandroid.app.App

/**
 *@author: lh
 *CreateDate: 2020/7/20
 */
object ImageLoader {

    private var isNotLoadImageWithoutWifi: Boolean = false
        get() {

            return SettingUtil.getIsNotLoadImageWithoutWifi()
        }
    fun load(context: Context, url: String?, iv: ImageView?){

        if (isNotLoadImageWithoutWifi && !NetWorkUtil.isWifi(App.context)){
            return
        }
        iv?.apply {
            Glide.with(context!!).clear(iv)
            val options = RequestOptions
                .diskCacheStrategyOf(DiskCacheStrategy.DATA)//解码前将数据写入磁盘缓存
                .placeholder(R.drawable.bg_placeholder)//占位符

            Glide.with(context!!)
                .load(url)
                .transition(DrawableTransitionOptions().crossFade())
                .apply(options)
                .into(iv)
        }

    }
}