package top.wsure.guild.unofficial.dtos

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import top.wsure.guild.common.utils.TimeUtils.toEpochMilli
import top.wsure.guild.common.utils.TimeUtils.toLocalDateTime
import java.time.LocalDateTime

@Serializable
data class GuildSender(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("user_id")
    val userId: Long
)

@Serializable
data class GroupSender(
    val age: Int,
    val area: String,
    val card: String,
    val level: String,
    val nickname: String,
    val role: String,
    val sex: String,
    val title: String,
    @SerialName("user_id")
    val userId: Long
)

@Serializable
data class Anonymous(
    val id:	Long , //	匿名用户 ID
    val name:	String , //	匿名用户名称
    val flag:	String , //	匿名用户 flag, 在调用禁言 API 时需要传入
)


@Serializable
data class RoleInfo(
    @SerialName("role_id")
    val roleId: String? = null,
    @SerialName("role_name")
    val roleName: String? = null
)

object GoCQHttpTimeSerializer: KSerializer<LocalDateTime> {
    override fun deserialize(decoder: Decoder): LocalDateTime {
        return kotlin.runCatching {
            decoder.decodeLong().toLocalDateTime()
        }.getOrElse { LocalDateTime.now() }
    }

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("goCQHttpTimeSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDateTime) {
        encoder.encodeLong(value.toEpochMilli())
    }

}