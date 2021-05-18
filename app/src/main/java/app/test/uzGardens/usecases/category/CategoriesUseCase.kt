package app.test.uzGardens.usecases.category

import app.test.uzGardens.network.model.CategoryResource

interface CategoriesUseCase {
    suspend operator fun invoke(): CategoryResource
}