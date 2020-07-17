package com.lh.wanandroid

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.BaseLoadMoreModule
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.bean.Article

/**
 *@author: lh
 *CreateDate: 2020/7/16
 */
class HomeAdapter(articleList: MutableList<Article>): BaseQuickAdapter<Article, BaseViewHolder>(R.layout.item_home_list, articleList),
    LoadMoreModule {
    override fun convert(holder: BaseViewHolder, item: Article) {



        val tvTopValue = item.top
        val tvFreshValue = item.fresh
        val tvTagValue = item.tags
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


        holder.getView<TextView>(R.id.tvArticleTop).visibility = if (tvTopValue == "1") View.VISIBLE else View.GONE
        holder.getView<TextView>(R.id.tvArticleFresh).visibility = if (tvFreshValue) View.VISIBLE else View.GONE
        holder.getView<TextView>(R.id.tvArticleAuthor).text = tvAuthorValue
        holder.getView<TextView>(R.id.tvArticleTag).run {
            if (tvTagValue.isNotEmpty()) {
                text = tvTagValue[0].name
                visibility = View.VISIBLE
            } else visibility = View.GONE
        }
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
        holder.getView<ImageView>(R.id.ivLike).setImageResource(if (isLiker)R.drawable.ic_like else R.drawable.ic_like_not)
    }

}