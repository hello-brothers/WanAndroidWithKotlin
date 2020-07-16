package com.lh.wanandroid.constant

import com.lh.wanandroid.utils.Preference
import java.lang.StringBuilder

/**
 *@author: lh
 *CreateDate: 2020/7/13
 */
object HttpConstant {

    const val COLLECTIONS_WEBSITE = "lg/collect"
    const val UNCOLLECTIONS_WEBSITE = "lg/uncollect"
    const val ARTICLE_WEBSITE = "article"
    const val TODO_WEBSITE = "lg/todo"
    const val COIN_WEBSITE = "lg/coin"

    const val SAVE_USER_LOGIN_KEY = "user/login"
    const val SAVE_USER_REGISTER_KEY = "user/register"

    const val SET_COOKIE_KEY = "set-cookie"


    const val COOKIE_NAME = "cookie"

    const val DEFAULT_TIMEOUT: Long = 15
    /** 设置最大缓存为50M **/
    const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 50

    fun encodeCookie(cookies: List<String>) = StringBuilder().apply{
        val set = HashSet<String>().apply {
            cookies.map { cookie ->
                cookie.split(";".toRegex()).dropLastWhile {
                    it.isEmpty()
                }.toTypedArray()
            }
                .forEach{
                    it.filterNot {
                        contains(it)
                    }.forEach {
                        add(it)
                    }
                }
        }

        val iterator = set.iterator()
        while (iterator.hasNext()){
            val cookie = iterator.next()
            append(cookie).append(";")
        }

        val last = lastIndexOf(";")
        if (last == length - 1){
            deleteCharAt(last)
        }
    }.toString()

    fun saveCookie(url: String?, domain: String?, cookie: String){
        url ?: return
        var spUrl by Preference(url, cookie)
        spUrl = cookie

        domain ?: return
        var spDomain by Preference(domain, cookie)
        spDomain = cookie

    }
}