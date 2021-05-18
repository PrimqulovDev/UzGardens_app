package app.test.uzGardens.di.modules


import android.content.Context
import app.test.uzGardens.BuildConfig
import app.test.uzGardens.network.Api
import app.test.uzGardens.network.interceptor.AppInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Converter
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Provides
    @Singleton
    fun provideChuckInterceptor(
        @ApplicationContext context: Context
    ): ChuckInterceptor = ChuckInterceptor(context)

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor {
        Timber.tag("NETWORK").d(it)
    }.apply {
        setLevel(if (BuildConfig.DEBUG) BODY else NONE)
    }


    @Provides
    @Singleton
    @ExperimentalSerializationApi
    fun provideFactory(): Converter.Factory {
        val contentType = "application/json".toMediaType()
        return Json {
            ignoreUnknownKeys = true
        }.asConverterFactory(contentType)
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        factory: Converter.Factory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(factory)
        .build()


    @Provides
    @Singleton
    fun provideClient(
        appInterceptor: AppInterceptor,
        chuckInterceptor: ChuckInterceptor,
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient().newBuilder()
        .retryOnConnectionFailure(false)
        .connectTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(chuckInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(appInterceptor)
        .build()


    @Provides
    @Singleton
    fun provideMainAPI(
        retrofit: Retrofit
    ): Api = retrofit.create(Api::class.java)


}