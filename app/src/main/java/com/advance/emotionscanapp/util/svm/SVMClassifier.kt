package com.advance.emotionscanapp.util.svm

import android.content.Context
import com.advance.emotionscanapp.util.log.Log
import org.json.JSONObject
import java.nio.charset.StandardCharsets

object SVMClassifier {

    private val TAG = SVMClassifier::javaClass.name

    private lateinit var mWeights: FloatArray

    private var mBias = 0F

    private var isMoreLoaded = false

    fun init(context: Context) {
        loadModelFromAssets(context)
    }

    private fun loadModelFromAssets(context: Context) {
        try {
            val inputStream = context.assets.open(SVMConstant.SVM_MODEL)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonString = String(buffer, StandardCharsets.UTF_8)
            val jsonObj = JSONObject(jsonString)

            this.mBias = jsonObj.getDouble(SVMConstant.SVM_BIAS).toFloat()

            val jsonWeights = jsonObj.getJSONArray(SVMConstant.SVM_WEIGHTS)
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

    fun predict(input: FloatArray): String {

        if (!isMoreLoaded) {
            return "Model Error"
        }

        var score = mBias

        for (i in 0 until input.size) {
            score += mWeights[i] * input[i]
        }

        if (score >= 0.0f) {
            return "Happy (+1)"
        } else {
            return "Sad (-1)"
        }

    }

}