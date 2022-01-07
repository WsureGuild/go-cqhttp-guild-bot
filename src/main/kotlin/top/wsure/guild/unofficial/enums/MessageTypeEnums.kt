package top.wsure.guild.unofficial.enums

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MessageTypeEnums {
    @SerialName("guild")
    GUILD,
    @SerialName("group")
    GROUP,
    @SerialName("private")
    PRIVATE
}