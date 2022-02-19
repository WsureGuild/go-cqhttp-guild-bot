package top.wsure.guild.unofficial.dtos.api
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import top.wsure.guild.unofficial.dtos.GoCQHttpTimeSerializer
import java.time.LocalDateTime


@Serializable
data class GuildInfo(
    @SerialName("create_time")
    @Serializable(with =GoCQHttpTimeSerializer::class )
    val createTime: LocalDateTime,
    @SerialName("guild_id")
    val guildId: String? = null,
    @SerialName("guild_name")
    val guildName: String? = null,
    @SerialName("guild_profile")
    val guildProfile: String? = null,
    @SerialName("max_admin_count")
    val maxAdminCount: Int? = null,
    @SerialName("max_member_count")
    val maxMemberCount: Int? = null,
    @SerialName("max_robot_count")
    val maxRobotCount: Int? = null,
    @SerialName("member_count")
    val memberCount: Int? = null,
    @SerialName("owner_id")
    val ownerId: String? = null
)