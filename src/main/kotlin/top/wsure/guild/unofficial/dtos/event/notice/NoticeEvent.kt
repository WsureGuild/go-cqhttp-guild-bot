package top.wsure.guild.unofficial.dtos.event.notice

import top.wsure.guild.unofficial.dtos.event.BaseEventDto
import top.wsure.guild.unofficial.enums.NoticeTypeEnums
import top.wsure.guild.unofficial.enums.PostTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class NoticeEvent(
    @SerialName("notice_type")
    val noticeType:NoticeTypeEnums
): BaseEventDto(PostTypeEnum.NOTICE)