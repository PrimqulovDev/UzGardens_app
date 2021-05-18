package app.test.uzGardens.network.interceptor


import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import app.test.uzGardens.utils.cache.AppCache
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AppInterceptor @Inject constructor(
    private val appCache: AppCache,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()

        val requestBuilder = original.newBuilder()
//            .header("Content-Type", "application/json")
            .header("Accept", "application/json")
            .header("Authorization", "${appCache.tokenType} ${appCache.token}")
            .header("Connection", "close")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}