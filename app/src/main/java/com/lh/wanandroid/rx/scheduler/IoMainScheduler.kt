package com.lh.wanandroid.rx.scheduler

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 *@author: lh
 *CreateDate: 2020/7/15
 */
class IoMainScheduler<T>(): BaseScheduler<T>(Schedulers.io(), AndroidSchedulers.mainThread())