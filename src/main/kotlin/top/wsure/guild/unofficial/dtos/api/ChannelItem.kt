package top.wsure.guild.unofficial.dtos.api
import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName
import top.wsure.guild.unofficial.dtos.GoCQHttpTimeSerializer
import java.time.LocalDateTime


@Serializable
data class ChannelItem(
    @SerialName("channel_id")
    val channelId: String,
    @SerialName("channel_name")
    val channelName: String,
    @SerialName("channel_type")
    val channelType: Int,
    @SerialName("create_time")
    @Serializable(with = GoCQHttpTimeSerializer::class )
    val createTime: LocalDateTime? = null,
    @SerialName("creator_id")
    val creatorId: Int? = null,
    @SerialName("creator_tiny_id")
    val creatorTinyId: String? = null,
    @SerialName("current_slow_mode")
    val currentSlowMode: Int? = null,
    @SerialName("owner_guild_id")
    val ownerGuildId: String? = null,
    @SerialName("slow_modes")
    val slowModes: List<SlowMode>? = null,
    @SerialName("talk_permission")
    val talkPermission: Int? = null,
    @SerialName("visible_type")
    val visibleType: Int? = null
)

@Serializable
data class SlowMode(
    @SerialName("slow_mode_circle")
    val slowModeCircle: Int? = null,
    @SerialName("slow_mode_key")
    val slowModeKey: Int? = null,
    @SerialName("slow_mode_text")
    val slowModeText: String? = null,
    @SerialName("speak_frequency")
    val speakFrequency: Int? = null
)