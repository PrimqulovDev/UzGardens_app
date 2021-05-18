package app.test.uzGardens.di.modules

import app.test.uzGardens.usecases.category.CategoriesUseCase
import app.test.uzGardens.usecases.category.CategoriesUseCaseImpl
import app.test.uzGardens.utils.cache.AppCache
import app.test.uzGardens.utils.cache.AppCacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    @Singleton
    fun bindAppCache(cache: AppCacheImpl): AppCache

    @Binds
    @Singleton
    fun bindCategoriesUseCase(useCase: CategoriesUseCaseImpl): CategoriesUseCase
}