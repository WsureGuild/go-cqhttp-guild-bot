package top.wsure.guild.unofficial.dtos.event.message

import top.wsure.guild.unofficial.dtos.Anonymous
import top.wsure.guild.unofficial.dtos.GroupSender
import kotlinx.serialization.Serializable

@Serializable
data class GroupMessage(
    val anonymous: Anonymous?,
    val font: Int,
    val group_id: Long,
    val message: String,
    val message_id: Long,
    val message_seq: Long,
    val message_type: String,
    val post_type: String,
    val raw_message: String,
    val self_id: Long,
    val sender: GroupSender,
    val sub_type: String,
    val time: Long,
    val user_id: Long
)

