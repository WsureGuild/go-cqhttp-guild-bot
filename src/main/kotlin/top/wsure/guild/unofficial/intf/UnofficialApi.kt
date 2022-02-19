package top.wsure.guild.unofficial.intf

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.guild.common.sender.Sender
import top.wsure.guild.common.utils.JsonUtils.jsonToObjectOrNull
import top.wsure.guild.common.utils.JsonUtils.objectToJson
import top.wsure.guild.common.utils.OkHttpUtils
import top.wsure.guild.unofficial.dtos.RoleInfo
import top.wsure.guild.unofficial.dtos.api.ChannelItem
import top.wsure.guild.unofficial.dtos.api.GuildInfo
import top.wsure.guild.unofficial.dtos.api.MemberProfile
import top.wsure.guild.unofficial.dtos.api.SendGuildChannelMsg
import top.wsure.guild.unofficial.dtos.event.BaseApiRes

class UnofficialApi(val host:String = "http://127.0.0.1:5700"):Sender {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    val SEND_GUILD_CHANNEL_MSG :String = "$host/send_guild_channel_msg"
    val GET_GUILD_CHANNEL_LIST :String = "$host/get_guild_channel_list?guild_id={{guild_id}}"
    val GET_GUILD_META_BY_GUEST :String = "$host/get_guild_meta_by_guest?guild_id={{guild_id}}"
    val GET_GUILD_MEMBER_PROFILE :String = "$host/get_guild_member_profile?guild_id={{guild_id}}&user_id={{user_id}}"
    val GET_GUILD_ROLES :String = "$host/get_guild_roles?guild_id={{guild_id}}"

    fun sendGuildChannelMsg( msg: SendGuildChannelMsg): BaseApiRes<SendGuildChannelMsg.Response>? {
        val json = msg.objectToJson()
        val res = OkHttpUtils.postJson(SEND_GUILD_CHANNEL_MSG, OkHttpUtils.addJson(json))
        return res.jsonToObjectOrNull<BaseApiRes<SendGuildChannelMsg.Response>>()
    }
    fun getGuildChannelList( guildId: String): BaseApiRes<List<ChannelItem>>? {
        val url = GET_GUILD_CHANNEL_LIST.replace("{{guild_id}}", guildId)
        val res = OkHttpUtils.getJson(url)
        return res.jsonToObjectOrNull<BaseApiRes<List<ChannelItem>>>()
    }

    fun getGuildMetaByGuest( guildId: String): BaseApiRes<GuildInfo>? {
        val url = GET_GUILD_META_BY_GUEST.replace("{{guild_id}}", guildId)
        val res = OkHttpUtils.getJson(url)
        return res.jsonToObjectOrNull<BaseApiRes<GuildInfo>>()
    }

    fun getGuildMemberProfile( guildId: String, userId: String): BaseApiRes<MemberProfile>? {
        val url = GET_GUILD_MEMBER_PROFILE.replace("{{guild_id}}", guildId)
            .replace("{{user_id}}", userId)
        val res = OkHttpUtils.getJson(url)
        return res.jsonToObjectOrNull<BaseApiRes<MemberProfile>>()
    }

    fun getGuildRoles( guildId: String): BaseApiRes<List<RoleInfo>>?{
        val url = GET_GUILD_ROLES.replace("{{guild_id}}", guildId)
        val res = OkHttpUtils.getJson(url)
        return res.jsonToObjectOrNull<BaseApiRes<List<RoleInfo>>>()
    }

}