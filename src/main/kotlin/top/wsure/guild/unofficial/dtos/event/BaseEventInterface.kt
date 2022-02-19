package top.wsure.guild.unofficial.dtos.event

interface BaseEventInterface {
    val guildId: String

    val channelId: Long

    val userId: Long
}