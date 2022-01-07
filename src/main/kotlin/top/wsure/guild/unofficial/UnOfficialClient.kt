package top.wsure.guild.unofficial

import top.wsure.guild.unofficial.dtos.api.BaseAction
import top.wsure.guild.unofficial.dtos.api.SendGroupMsg
import top.wsure.guild.unofficial.dtos.api.SendGuildChannelMsg
import top.wsure.guild.unofficial.dtos.event.BaseApiRes
import top.wsure.guild.unofficial.dtos.event.BaseEventDto
import top.wsure.guild.unofficial.dtos.event.GoCQApiRes
import top.wsure.guild.unofficial.dtos.event.message.GroupMessage
import top.wsure.guild.unofficial.dtos.event.message.GuildMessage
import top.wsure.guild.unofficial.dtos.event.message.MessageEvent
import top.wsure.guild.unofficial.enums.ActionEnums
import top.wsure.guild.unofficial.enums.MessageTypeEnums
import top.wsure.guild.unofficial.enums.PostTypeEnum
import top.wsure.guild.unofficial.intf.UnOfficialBotEvent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import top.wsure.guild.common.client.WebsocketClient
import top.wsure.guild.common.utils.AsyncUtils
import top.wsure.guild.common.utils.JsonUtils.jsonToObject
import top.wsure.guild.common.utils.JsonUtils.jsonToObjectOrNull
import top.wsure.guild.common.utils.JsonUtils.objectToJson

class UnOfficialClient(private val unofficialEvents:List<UnOfficialBotEvent> = emptyList(),
                       private val wsUrl :String = "ws://127.0.0.1:6700",
                       private val retryWait: Long = 3000,
):WebsocketClient(wsUrl,retryWait) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    private val asyncUtil = AsyncUtils<String>()

    lateinit var sender:UnofficialMessageSender

    override fun onOpen(webSocket: WebSocket, response: Response) {
        logger.info("onOpen ")
        connected()
        setSenderToListener(webSocket)
    }

    private fun setSenderToListener(webSocket: WebSocket) {
        sender = UnofficialMessageSender(webSocket, asyncUtil)
        unofficialEvents.forEach {
            it.sender = sender
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onMessage(webSocket: WebSocket, text: String) {
        kotlin.runCatching {
            connected()
            logger.debug("received message $text")
            text.jsonToObjectOrNull<BaseEventDto>(false)?.also { event->
                when (event.postType){
                    PostTypeEnum.MESSAGE -> {
                        text.jsonToObjectOrNull<MessageEvent>()?.also { message ->
                            when(message.messageType){
                                MessageTypeEnums.GUILD -> {
                                    text.jsonToObjectOrNull<GuildMessage>()?.also { guildMessage ->
                                        logger.debug("received GUILD_MESSAGE $text")
                                        unofficialEvents.onEach { GlobalScope.launch { it.onGuildMessage(guildMessage) } }
                                    }
                                }
                                MessageTypeEnums.GROUP -> {
                                    text.jsonToObjectOrNull<GroupMessage>()?.also { groupMessage ->
                                        logger.debug("received GROUP_MESSAGE $text")
                                        unofficialEvents.onEach { GlobalScope.launch { it.onGroupMessage(groupMessage) } }
                                    }
                                }
                            }
                        }
                    }
                    PostTypeEnum.NOTICE -> {

                    }
                }
            }
            text.jsonToObjectOrNull<GoCQApiRes>(false)?.also { wsRes ->
                logger.debug("received message $text")
                asyncUtil.onReceive(wsRes.echo,text)
            }
        }.onFailure {
            it.printStackTrace()
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        logger.warn("onClosing code:$code reason:$reason")
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        logger.warn("onClosed code:$code reason:$reason")

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        logger.error("onFailure response:${response?.message} ",t)
        reconnect()
    }

}
class UnofficialMessageSender(val webSocket: WebSocket, val asyncUtils: AsyncUtils<String>){
    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    fun sendMessage(text: String){
        logger.info("send message:$text")
        webSocket.send(text)
    }
    private inline fun <reified T: BaseAction<*>> sendMessage(text:T){
        val message = text.objectToJson()
        logger.info("send message:$message")
        webSocket.send(message)
    }
    private suspend inline fun <reified T: BaseAction<*>,reified R> sendMessageAsync(text:T): BaseApiRes<R> {
        val message = text.objectToJson()
        logger.info("send message:$message")
        webSocket.send(message)
        return asyncUtils.sendAndWaitResult<String>(text.echo).jsonToObject()
    }


    suspend fun sendGroupMsgAsync(msg: SendGroupMsg): SendGroupMsg.Response{
        val msgReq = BaseAction(ActionEnums.SEND_GROUP_MSG,msg)
        return sendMessageAsync<BaseAction<SendGroupMsg>, SendGroupMsg.Response>(msgReq).data
    }

    suspend fun sendGuildChannelMsgAsync(msg: SendGuildChannelMsg): SendGuildChannelMsg.Response{
        val msgReq = BaseAction(ActionEnums.SEND_GUILD_CHANNEL_MSG,msg)
        return sendMessageAsync<BaseAction<SendGuildChannelMsg>, SendGuildChannelMsg.Response>(msgReq).data
    }
}