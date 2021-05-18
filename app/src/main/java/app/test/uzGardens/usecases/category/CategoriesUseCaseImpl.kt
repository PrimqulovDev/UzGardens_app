package app.test.uzGardens.usecases.category

import app.test.uzGardens.base.BaseUseCase
import app.test.uzGardens.network.model.CategoryResource
import app.test.uzGardens.repository.MainRepository
import app.test.uzGardens.utils.cache.AppCache
import javax.inject.Inject

class CategoriesUseCaseImpl @Inject constructor(
    appCache: AppCache,
    private val repository: MainRepository
) : BaseUseCase(appCache), CategoriesUseCase {

    override suspend fun invoke(): CategoryResource = handle {
        repository.getCategories()
    }

}