package com.advance.emotionscanapp.data.utils

data class DbError(
    val errorCode: Int
): BaseDb() {

    override fun getResultCode(): Int {
        return errorCode
    }

    override fun getInfo(): String {
        return when(errorCode) {
            ErrorCode.RESULT_ID_DB_ER001 -> ErrorMessage.ERR_STR_DB_ER001
            ErrorCode.RESULT_ID_DB_ER002 -> ErrorMessage.ERR_STR_DB_ER002
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

        const val RESULT_ID_DB_OTHERS: Int = 0xEFFFF

    }

}

sealed class ErrorMessage {

    companion object {

        const val ERR_STR_DB_ER001 = "Data is not exist."

        const val ERR_STR_DB_ER002 = "Could not execute the command."

        const val ERR_STR_DB_OTHERS = "Unknown error."

    }

}
