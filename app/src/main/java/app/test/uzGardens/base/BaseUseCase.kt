package app.test.uzGardens.base

import app.test.uzGardens.network.NetworkException
import app.test.uzGardens.network.UnknownErrorException
import app.test.uzGardens.network.ValidationErrorException
import app.test.uzGardens.network.model.BaseResponse
import app.test.uzGardens.network.model.UIResource
import app.test.uzGardens.utils.cache.AppCache
import retrofit2.Response
import timber.log.Timber

abstract class BaseUseCase(
    protected val appCache: AppCache
) {

    protected suspend fun <T> handle(
        body: suspend () -> Response<BaseResponse<T>>
    ): UIResource<T> {

        return try {
            val response: Response<BaseResponse<T>> = body()
            handleResource(response)
        } catch (e: Exception) {
            Timber.e(e)
            when (e) {
                is NullPointerException -> UIResource.Error(e)
                else -> UIResource.Error(e)
            }
        }
    }

    private fun <T> handleResource(response: Response<BaseResponse<T>>): UIResource<T> {

        val body: BaseResponse<T>? = response.body()
        val data: T? = body?.data
        val code: Int? = body?.code
        return when (response.code()) {
            200 -> {
                if (data != null) UIResource.Resource(data, response.code())
                else UIResource.Error(NullPointerException("Response data must not be null"))
            }
            400 -> when (code) {
                55001 -> UIResource.Error(
                    ValidationErrorException(
                        data.toString(),
                        code
                    )
                )
                else -> UIResource.Error(UnknownErrorException(data.toString(), code))
            }

            else -> UIResource.Error(NetworkException(data.toString()))

        }

    }

}