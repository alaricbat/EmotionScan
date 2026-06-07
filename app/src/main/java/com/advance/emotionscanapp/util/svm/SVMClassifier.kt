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

    private lateinit var listener: SVMClassifierListener

    fun init(context: Context) {
        loadModelFromAssets(context)
    }

    fun setListener(listener: SVMClassifierListener) {
        this.listener = listener
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

    fun predict(input: FloatArray) {
        Log.funIn(TAG, "predict")

        if (!isMoreLoaded) {
            listener.onError("Loaded state is error.")
            Log.funOut(TAG, "predict")
            return
        }

        try {
            var score = mBias

            for (i in 0 until input.size) {
                score += mWeights[i] * input[i]
            }
            Log.funOut(TAG, "[predict]: score = $score")
            listener.onScored(score)

            var result = SVMClassifierLabel.LABEL_SAD

            if (score >= 0.0f) {
                result = SVMClassifierLabel.LABEL_HAPPY
            }

            listener.onPredicted(result)
        } catch (e: Exception) {
            e.message?.let { listener.onError(it) }
        }

        Log.funOut(TAG, "predict")

    }

    interface SVMClassifierListener {

        fun onPredicted(label: SVMClassifierLabel)

        fun onScored(score: Float)

        fun onError(msg: String)

    }

    enum class SVMClassifierLabel {
        LABEL_HAPPY,
        LABEL_SAD
    }

}