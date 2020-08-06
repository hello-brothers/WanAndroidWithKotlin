package com.lh.wanandroid.adapter

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.R
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.utils.ImageLoader

/**
 * Author: lh
 * Description:
 * Data: 2020/8/5
 */
class ProjectListAdapter(dataList: MutableList<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_project, dataList), LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: Article) {

        holder.run {
            setText(R.id.tvProjectTitle, item.title)
            setText(R.id.tvProjectDesc, item.desc)
            setText(R.id.tvProjectAuthor, if (item.author.isNotEmpty()) item.author else item.shareUser)
            setText(R.id.tvProjectTime, item.niceDate)
            setImageResource(R.id.ivProjectLike, if (item.collect) R.drawable.ic_like else R.drawable.ic_like_not)

            getView<ImageView>(R.id.ivProject).let {
                ImageLoader.load(context, item.envelopePic, it)
            }
        }
    }

}