package app.test.uzGardens.di.modules

import android.content.Context
import android.content.SharedPreferences
import app.test.uzGardens.utils.Const.APP_CACHE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    @AppCacheQualifier
    fun provideAppCacheSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences(APP_CACHE, Context.MODE_PRIVATE)
}