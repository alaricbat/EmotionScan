package com.advance.emotionscanapp.data.result

abstract class BaseResultInfo(
    val resultCode: Int
){

    private lateinit var _exMsg: String

    var exMsg: String = ""
        set(value) {
            field = value
            _exMsg = value
        }

    abstract fun getResultCode(): Int

    abstract fun getInfo(): String

    abstract fun isSuccess(): Boolean

}