package com.advance.emotionscanapp.data.utils

class DbSuccess(
    val successCode: Int
): BaseDb() {

    override fun getResultCode(): Int {
        return successCode
    }

    override fun getInfo(): String {
        return when(successCode) {
            SuccessCode.RESULT_ID_DB_INSERT_SUCCESS -> SuccessMessage.COMPLETE_STR_DB_C001
            SuccessCode.RESULT_ID_DB_UPDATE_SUCCESS -> SuccessMessage.COMPLETE_STR_DB_C002
            SuccessCode.RESULT_ID_DB_DELETE_SUCCESS -> SuccessMessage.COMPLETE_STR_DB_C003
            SuccessCode.RESULT_ID_DB_GET_ITEM_SUCCESS -> SuccessMessage.COMPLETE_STR_DB_C004
            SuccessCode.RESULT_ID_DB_GET_ALL_SUCCESS -> SuccessMessage.COMPLETE_STR_DB_C005
            else -> SuccessMessage.COMPLETE_STR_DB_C000
        }
    }

    override fun isSuccess(): Boolean {
        return true
    }

}

class SuccessCode {

    companion object {

        const val RESULT_ID_DB_INSERT_SUCCESS: Int = 0xC001

        const val RESULT_ID_DB_UPDATE_SUCCESS: Int = 0xC002

        const val RESULT_ID_DB_DELETE_SUCCESS: Int = 0xC003

        const val RESULT_ID_DB_GET_ITEM_SUCCESS: Int = 0xC004

        const val RESULT_ID_DB_GET_ALL_SUCCESS: Int = 0xC005

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