package com.advance.emotionscanapp.data.result

class ResultInfoSuccess(
    successCode: Int
): BaseResultInfo(resultCode = successCode) {

    override fun getResultCode(): Int {
        return resultCode
    }

    override fun getInfo(): String {
        return when(resultCode) {
            SuccessCode.RESULT_ID_DB_C001 -> SuccessMessage.COMPLETE_STR_DB_C001
            SuccessCode.RESULT_ID_DB_C002 -> SuccessMessage.COMPLETE_STR_DB_C002
            SuccessCode.RESULT_ID_DB_C003 -> SuccessMessage.COMPLETE_STR_DB_C003
            SuccessCode.RESULT_ID_DB_C004 -> SuccessMessage.COMPLETE_STR_DB_C004
            SuccessCode.RESULT_ID_DB_C005 -> SuccessMessage.COMPLETE_STR_DB_C005
            else -> SuccessMessage.COMPLETE_STR_DB_C000
        }
    }

    override fun isSuccess(): Boolean {
        return true
    }

}

class SuccessCode {

    companion object {

        const val RESULT_ID_DB_C001: Int = 0xC001

        const val RESULT_ID_DB_C002: Int = 0xC002

        const val RESULT_ID_DB_C003: Int = 0xC003

        const val RESULT_ID_DB_C004: Int = 0xC004

        const val RESULT_ID_DB_C005: Int = 0xC005

        const val RESULT_ID_DB_OTHERS: Int = 0xC000

    }

}

sealed class SuccessMessage {

    companion object {

        const val COMPLETE_STR_DB_C001 = "Insert db completed."

        const val COMPLETE_STR_DB_C002 = "Update db completed."

        const val COMPLETE_STR_DB_C003 = "Delete db completed."

        const val COMPLETE_STR_DB_C004 = "Get item by id completed."

        const val COMPLETE_STR_DB_C005 = "Get items completed."

        const val COMPLETE_STR_DB_C000 = "Execute db completed."

    }

}