package com.lh.wanandroid.rx

import com.lh.wanandroid.rx.scheduler.IoMainScheduler

/**
 *@author: lh
 *CreateDate: 2020/7/15
 */
object SchedulerUtils {


    fun <T> ioToMain(): IoMainScheduler<T> = IoMainScheduler()


}