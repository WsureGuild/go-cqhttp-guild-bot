package top.wsure.guild.unofficial.dtos.api

import top.wsure.guild.unofficial.enums.ActionEnums
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class BaseAction<T>(
    val action:ActionEnums,
    val params:T,
    val echo:String = UUID.randomUUID().toString(),
)