package com.advance.emotionscanapp.data.utils

abstract class BaseDb {

    abstract fun getResultCode(): Int

    abstract fun getInfo(): String

    abstract fun isSuccess(): Boolean

}