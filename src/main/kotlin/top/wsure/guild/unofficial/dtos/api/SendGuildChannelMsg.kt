package top.wsure.guild.unofficial.dtos.api

import top.wsure.guild.unofficial.dtos.event.BaseEventInterface
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SendGuildChannelMsg(
    @SerialName("guild_id") val guildId:String,
    @SerialName("channel_id") val channelId:Long,
    val message:String
){
    @Serializable
    data class Response(
        @SerialName("message_id")
        val messageId : String
    )
}

fun BaseEventInterface.toSendGuildChannelMsgAction(message:String):SendGuildChannelMsg{
    return SendGuildChannelMsg(this.guildId,this.channelId,message)
}