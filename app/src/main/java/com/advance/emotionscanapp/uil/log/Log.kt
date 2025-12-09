package com.advance.emotionscanapp.uil.log

import android.util.Log
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Log {

    companion object {

        private val TAG = "EmotionScan[${LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}]"



        fun i(tagName: String, msg: String) {
            Log.i("$TAG[$tagName]", msg)
        }

        fun d(tagName: String, msg: String) {
            Log.d("$TAG[$tagName]", msg)
        }

        fun e(tagName: String, msg: String) {
            Log.e("$TAG[$tagName]", msg)
        }

        fun w(tagName: String, msg: String) {
            Log.w("$TAG[$tagName]", msg)
        }

        fun v(tagName: String, msg: String) {
            Log.v("$TAG[$tagName]", msg)
        }

        fun funIn(tagName: String, msg: String) {
            Log.d("$TAG[$tagName]", "$msg [IN]")
        }

        fun funOut(tagName: String, msg: String) {
            Log.d("$TAG[$tagName]", "$msg [OUT]")
        }

    }

}