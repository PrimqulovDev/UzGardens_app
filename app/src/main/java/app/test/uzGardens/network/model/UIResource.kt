package app.test.uzGardens.network.model


sealed class UIResource<out T> {
    object Loading : UIResource<Nothing>()
    data class Resource<T>(val data: T, val statusCode: Int = 200) : UIResource<T>()
    data class Error(val error: Throwable) : UIResource<Nothing>()
}