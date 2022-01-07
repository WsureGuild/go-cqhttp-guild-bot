package top.wsure.guild.unofficial.dtos.event.message

import top.wsure.guild.unofficial.dtos.GuildSender
import top.wsure.guild.unofficial.dtos.event.BaseEventInterface
import top.wsure.guild.unofficial.enums.MessageTypeEnums
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildMessage(
    @SerialName("guild_id")
    override val guildId: Long,
    @SerialName("channel_id")
    override val channelId: Long,
    @SerialName("user_id")
    override val userId: Long,

    @SerialName("message")
    val message: String,
    @SerialName("message_id")
    val messageId: String,
    @SerialName("self_id")
    val selfId: Long,
    @SerialName("self_tiny_id")
    val selfTinyId: Long,
    @SerialName("sender")
    val sender: GuildSender,
    @SerialName("sub_type")
    val subType: String,
    @SerialName("time")
    val time: Long

): MessageEvent(MessageTypeEnums.GUILD) , BaseEventInterface

