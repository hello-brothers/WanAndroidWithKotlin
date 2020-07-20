package com.lh.wanandroid.mvp.presenter

import com.lh.wanandroid.base.BasePresenter
import com.lh.wanandroid.ext.deal
import com.lh.wanandroid.mvp.contract.HomeContract
import com.lh.wanandroid.mvp.model.HomeModel
import com.lh.wanandroid.mvp.model.bean.Article
import com.lh.wanandroid.mvp.model.bean.ArticleResponseBody
import com.lh.wanandroid.mvp.model.bean.HttpResult
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

/**
 *@author: lh
 *CreateDate: 2020/7/3
 */
class HomePresenter: BasePresenter<HomeContract.Model, HomeContract.View>(), HomeContract.Presenter {

    override fun createModel() = HomeModel()

    override fun requestArticles(pageNum: Int) {
        mModel?.requestArticles(pageNum)?.deal(mView){
            mView?.setArticles(it)
        }

    }

    override fun requestHomeData() {

        requestBanner()


        if (!isShowTopArticle())
            mModel?.requestArticles(0)
        else{
            Observable.zip(mModel?.requestTopArticles(), mModel?.requestArticles(0),
                BiFunction<HttpResult<MutableList<Article>>, HttpResult<ArticleResponseBody>, HttpResult<ArticleResponseBody>>{t1, t2 ->
                    t1.data.forEach {
                        it.top = "1"
                    }

                    t2.data.datas.addAll(0, t1.data)
                    t2
                })
        }
            ?.deal(mView){
            mView?.setArticles(it)

        }
    }

    override fun requestBanner() {
        mModel?.requestBanner()?.deal(mView){
            mView?.setBanner(it)
        }
    }

    private fun isShowTopArticle(): Boolean{

        return true
    }
}