package top.wsure.guild.unofficial.dtos.event.message

import top.wsure.guild.unofficial.dtos.event.BaseEventDto
import top.wsure.guild.unofficial.enums.MessageTypeEnums
import top.wsure.guild.unofficial.enums.PostTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class MessageEvent(
    @SerialName("message_type")
    val messageType:MessageTypeEnums,
    ): BaseEventDto(PostTypeEnum.MESSAGE)