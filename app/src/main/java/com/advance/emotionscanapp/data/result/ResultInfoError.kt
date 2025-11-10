package com.advance.emotionscanapp.data.result

class ResultInfoError(
    errorCode: Int
): BaseResultInfo(resultCode = errorCode) {

    override fun getResultCode(): Int {
        return resultCode
    }

    override fun getInfo(): String {
        return when(resultCode) {
            ErrorCode.RESULT_ID_DB_ER001 -> ErrorMessage.ERR_STR_DB_ER001
            ErrorCode.RESULT_ID_DB_ER002 -> ErrorMessage.ERR_STR_DB_ER002
            ErrorCode.RESULT_ID_DB_ER003 -> ErrorMessage.ERR_STR_DB_ER003
            ErrorCode.RESULT_ID_DB_ER004 -> ErrorMessage.ERR_STR_DB_ER004
            ErrorCode.RESULT_ID_DB_ER005 -> ErrorMessage.ERR_STR_DB_ER005
            else -> ErrorMessage.ERR_STR_DB_OTHERS
        }
    }

    override fun isSuccess(): Boolean {
        return false
    }

}

class ErrorCode {

    companion object {

        const val RESULT_ID_DB_ER001: Int = 0xE0001

        const val RESULT_ID_DB_ER002: Int = 0xE0002

        const val RESULT_ID_DB_ER003: Int = 0xE0003

        const val RESULT_ID_DB_ER004: Int = 0xE0004

        const val RESULT_ID_DB_ER005: Int = 0xE0005

        const val RESULT_ID_DB_ERFFF: Int = 0xEFFFF

    }

}

sealed class ErrorMessage {

    companion object {

        const val ERR_STR_DB_ER001 = "Insert db failed."

        const val ERR_STR_DB_ER002 = "Update db failed."

        const val ERR_STR_DB_ER003 = "Delete db failed."

        const val ERR_STR_DB_ER004 = "Get item by id failed."

        const val ERR_STR_DB_ER005 = "Get items failed."

        const val ERR_STR_DB_OTHERS = "Unknown error."

    }

}
