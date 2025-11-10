package com.advance.emotionscanapp.data.result
 open class DbResult<out T>(
     private val data: T,
     private val resultInfo: BaseResultInfo
 ) {

     fun getData(): T = data

     fun getResultInfo(): BaseResultInfo = resultInfo

}