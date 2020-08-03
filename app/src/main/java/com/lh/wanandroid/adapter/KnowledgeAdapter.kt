package com.lh.wanandroid.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.IdRes
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.R
import com.lh.wanandroid.mvp.model.bean.Article

/**
 * Author: lh
 * Description:
 * Data: 2020/8/3
 */
class KnowledgeAdapter(articleList: MutableList<Article>): BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home_list, articleList),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: Article) {

        /** 屏蔽所有item的标志 **/
        holder.run {
            getView<TextView>(R.id.tvArticleTop).visibility = View.GONE
            getView<TextView>(R.id.tvArticleFresh).visibility = View.GONE
            getView<TextView>(R.id.tvArticleTag).visibility = View.GONE

        }


        val tvAuthorValue = if (item.author.isNotEmpty()) item.author else item.shareUser
        val data = item.niceDate
        val thumbNaiValue = item.envelopePic
        val tvTitleValue = item.title
        val tvChapterValue = when{
            item.superChapterName.isNotEmpty() && item.chapterName.isNotEmpty() -> "${item.superChapterName}/${item.chapterName}"
            item.superChapterName.isNotEmpty() -> "${item.superChapterName}"
            item.chapterName.isNotEmpty() -> "${item.chapterName}"
            else -> ""
        }
        val isLiker = item.collect


        holder.getView<TextView>(R.id.tvArticleAuthor).text = tvAuthorValue
        holder.getView<TextView>(R.id.tvArticleData).text = data
        holder.getView<ImageView>(R.id.ivArticleThumbnail).run {
            visibility = if (thumbNaiValue.isNotEmpty()) {
                View.VISIBLE
                //                Glide.with(context!!)
                //                    .load(url)
                //                    .transition(DrawableTransitionOptions().crossFade())
                //                    .apply(options)
                //                    .into(iv)
            }else View.GONE
        }
        holder.getView<TextView>(R.id.tvArticleTitle).text = tvTitleValue
        holder.getView<TextView>(R.id.tvArticleChapter).text = tvChapterValue
        holder.getView<ImageView>(R.id.ivLike).setImageResource(if (isLiker) R.drawable.ic_like else R.drawable.ic_like_not)


    }


}