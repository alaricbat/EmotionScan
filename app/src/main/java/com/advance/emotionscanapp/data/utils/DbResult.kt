package com.advance.emotionscanapp.data.utils
 open class DbResult<out T>(
     private val data: T,
     private val resultDb: BaseDb
 ) {

     fun getData(): T = data

     fun isSuccess() = resultDb.isSuccess()

}