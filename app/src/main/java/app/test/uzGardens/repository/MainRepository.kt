package app.test.uzGardens.repository

import app.test.uzGardens.network.Api
import app.test.uzGardens.network.model.CategoryResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val mainApi: Api
) {

    suspend fun getCategories(): CategoryResponse =
        withContext(IO) { mainApi.getCategories() }

}