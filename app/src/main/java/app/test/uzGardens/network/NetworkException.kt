package app.test.uzGardens.network

class NetworkException(message: String, code: Int? = 0) : Exception("$message code = $code")
class UnknownErrorException(message: String, code: Int? = 0) : Exception("$message code = $code")
class ValidationErrorException(message: String, code: Int? = 0) : Exception("$message code = $code")