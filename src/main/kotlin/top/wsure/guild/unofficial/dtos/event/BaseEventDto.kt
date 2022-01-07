package top.wsure.guild.unofficial.dtos.event

import top.wsure.guild.unofficial.enums.PostTypeEnum
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class BaseEventDto(
    @SerialName("post_type")
    val postType: PostTypeEnum,
)