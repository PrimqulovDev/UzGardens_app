package app.test.uzGardens.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.Response

typealias CategoryResource = UIResource<List<CategoryDTO>>
typealias CategoryResponse = Response<BaseResponse<List<CategoryDTO>>>

@Serializable
data class CategoryDTO(

    @SerialName("_id")
    val id: String,

    @SerialName("created_time")
    val createdTime: String?,

    @SerialName("icon")
    val icon: String,

    @SerialName("image")
    val image: String,

    @SerialName("name")
    val name: String,

    @SerialName("shape")
    val shape: String,

    @SerialName("updated_time")
    val updatedTime: String?
)