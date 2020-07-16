package com.lh.wanandroid.http.function

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 *@author: lh
 *CreateDate: 2020/7/15
 * 请求重连
 */
class ReTryWithDelay(private val retryCount: Int = 3, private val retryDelayMills: Long = 3000): Function<Observable<out Throwable>, Observable<*>> {
    override fun apply(t: Observable<out Throwable>): Observable<*> {
        return t.zipWith(Observable.range(1, retryCount + 1), BiFunction<Throwable, Int, Wrapper> { t1, t2 -> Wrapper(t1, t2) })
            .flatMap {
                if (isRetry(it)){
                    Observable.timer(retryDelayMills * it.count, TimeUnit.MILLISECONDS)
                }else{
                    Observable.error<Any>(it.throwable)
                }
            }
    }

    private inner class Wrapper(val throwable: Throwable, val count: Int)

    private fun isRetry(wrapper: Wrapper): Boolean{

        val throwable = wrapper.throwable

        return wrapper.count < retryCount + 1
                && (throwable is ConnectException || throwable is TimeoutException || throwable is SocketTimeoutException || throwable is HttpException)



    }

}