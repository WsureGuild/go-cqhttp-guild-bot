package top.wsure.guild.unofficial.dtos.event

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BaseApiRes<T>(
    @SerialName("data")
    val `data`: T,
    @SerialName("echo")
    val echo: String,
    @SerialName("retcode")
    val retcode: Int,
    @SerialName("status")
    val status: ApiResStatus
)

@Serializable
enum class ApiResStatus{
    @SerialName("ok")
    OK,
    @SerialName("failed")
    FAILED,
    ;
}