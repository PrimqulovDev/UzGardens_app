package app.test.uzGardens.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseResponse<T>(

    @SerialName("data")
    val data: T?,

    @SerialName("message")
    val message: String? = null,

    @SerialName("code")
    val code: Int = 0
)