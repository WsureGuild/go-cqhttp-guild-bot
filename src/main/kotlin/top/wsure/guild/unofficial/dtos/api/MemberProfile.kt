package top.wsure.guild.unofficial.dtos.api
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import top.wsure.guild.unofficial.dtos.RoleInfo


@Serializable
data class MemberProfile(
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("join_time")
    val joinTime: Int? = null,
    @SerialName("nickname")
    val nickname: String? = null,
    @SerialName("roles")
    val roles: List<RoleInfo>? = null,
    @SerialName("tiny_id")
    val tinyId: String? = null
)
