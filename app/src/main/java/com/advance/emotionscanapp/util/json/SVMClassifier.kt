package com.advance.emotionscanapp.util.json

import android.content.Context
import com.advance.emotionscanapp.util.log.Log
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.StandardCharsets

class SVMClassifier {

    companion object {
        private val TAG = SVMClassifier::javaClass.name
    }

    private lateinit var mWeights: FloatArray

    private var mBias = 0F

    private var isMoreLoaded = false

    constructor(context: Context) {
        loadModelFromAssets(context)
    }

    private fun loadModelFromAssets(context: Context) {
        try {
            val inputStream = context.assets.open("svm_model.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonString = String(buffer, StandardCharsets.UTF_8)
            val jsonObj = JSONObject(jsonString)

            this.mBias = jsonObj.getDouble("bias").toFloat()

            val jsonWeights = jsonObj.getJSONArray("weights")
            this.mWeights = FloatArray(jsonWeights.length())
            for (i in 0 until jsonWeights.length()) {
                this.mWeights[i] = jsonWeights.getDouble(i).toFloat()
            }

            this.isMoreLoaded = true
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            this.isMoreLoaded = false
        }
    }

}