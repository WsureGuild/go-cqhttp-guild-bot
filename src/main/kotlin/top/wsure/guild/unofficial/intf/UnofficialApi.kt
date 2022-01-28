package top.wsure.guild.unofficial.intf

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.guild.common.sender.Sender
import top.wsure.guild.common.utils.JsonUtils.jsonToObjectOrNull
import top.wsure.guild.common.utils.JsonUtils.objectToJson
import top.wsure.guild.common.utils.OkHttpUtils
import top.wsure.guild.unofficial.dtos.api.ChannelItem
import top.wsure.guild.unofficial.dtos.api.SendGuildChannelMsg
import top.wsure.guild.unofficial.dtos.event.BaseApiRes

class UnofficialApi(val host:String = "http://127.0.0.1:5700"):Sender {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)
    val SEND_GUILD_CHANNEL_MSG :String = "$host/send_guild_channel_msg"
    val GET_GUILD_CHANNEL_LIST :String = "$host/get_guild_channel_list?guild_id={{guild_id}}"

    fun sendGuildChannelMsg( msg: SendGuildChannelMsg): BaseApiRes<SendGuildChannelMsg.Response>? {
        val json = msg.objectToJson()
        val res = OkHttpUtils.postJson(SEND_GUILD_CHANNEL_MSG, OkHttpUtils.addJson(json))
        return res.jsonToObjectOrNull<BaseApiRes<SendGuildChannelMsg.Response>>()
    }
    fun getGuildChannelList( guildId: Long): BaseApiRes<List<ChannelItem>>? {
        val url = GET_GUILD_CHANNEL_LIST.replace("{{guild_id}}",guildId.toString())
        val res = OkHttpUtils.getJson(url)
        return res.jsonToObjectOrNull<BaseApiRes<List<ChannelItem>>>()
    }

}