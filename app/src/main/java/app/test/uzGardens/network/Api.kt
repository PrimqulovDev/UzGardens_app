package app.test.uzGardens.network

import app.test.uzGardens.network.model.CategoryResponse
import retrofit2.http.POST

interface Api {

    @POST("category/get")
    suspend fun getCategories(): CategoryResponse

}