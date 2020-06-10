package grg.app.open.net

class ApiWrapper<DATA>(
    val success: Boolean = true,
    val errorCode: Int,
    val errorMsg: String,
    val data: DATA? = null
) {

    companion object {

        object Code {
            const val ERROR_UNKNOW = 0
        }

        fun error(message: String, code: Int = Code.ERROR_UNKNOW): ApiWrapper<*> {
            return ApiWrapper(
                false,
                code,
                message,
                null
            )
        }

    }

}