package top.wsure.guild.unofficial.intf

import top.wsure.guild.unofficial.UnofficialMessageSender
import top.wsure.guild.unofficial.dtos.event.message.GroupMessage
import top.wsure.guild.unofficial.dtos.event.message.GuildMessage

abstract class UnOfficialBotEvent {

    lateinit var sender: top.wsure.guild.unofficial.UnofficialMessageSender

    open suspend fun onGuildMessage(message:GuildMessage){

    }
    open suspend fun onGroupMessage(message:GroupMessage){

    }
}