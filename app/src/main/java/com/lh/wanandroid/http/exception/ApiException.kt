package com.lh.wanandroid.http.exception

/**
 *@author: lh
 *CreateDate: 2020/7/16
 */
class ApiException(val errorCode:Int, val errorMsg: String): Exception()