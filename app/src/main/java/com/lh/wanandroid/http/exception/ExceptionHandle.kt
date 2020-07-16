package com.lh.wanandroid.http.exception

import com.google.gson.JsonParseException
import org.json.JSONException
import retrofit2.HttpException
import java.lang.IllegalArgumentException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 *@author: lh
 *CreateDate: 2020/7/16
 */
class ExceptionHandle {

    companion object{

        var errorMsg = "请求失败，稍后重试！"
        var errorCode = ErrorStatus.UNKNOWN_ERROR
        fun handleException(t: Throwable): ApiException{

           when{
               t.isNetError() ->{
                   errorCode = ErrorStatus.NETWORK_ERROR
                   errorMsg = "网络连接异常"
               }

               t.isParseError() -> {
                   errorCode = ErrorStatus.SERVER_ERROR
                   errorMsg = "数据解析异常"
               }

               t.isIllegalArgError() ->{
                   errorCode = ErrorStatus.SERVER_ERROR
                   errorMsg = "参数错误"
               }

               else ->{
                   errorMsg = "未知错误，可能抛锚了吧~"
                   errorCode = ErrorStatus.UNKNOWN_ERROR
               }
           }

            return ApiException(errorCode, errorMsg)
        }

        private fun Throwable.isNetError()=
            this is SocketTimeoutException
                    || this is ConnectException
                    || this is HttpException
                    || this is UnknownHostException

        private fun Throwable.isParseError() =
            this is JsonParseException
                    || this is JSONException
                    || this is ParseException


        private fun Throwable.isIllegalArgError() =
            this is IllegalArgumentException

    }


}