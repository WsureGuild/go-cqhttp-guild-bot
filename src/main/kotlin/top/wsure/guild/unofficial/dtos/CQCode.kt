package top.wsure.guild.unofficial.dtos

object CQCode {
    val AT_PATTERN = Regex("(?<=\\[CQ:at,qq=)\\d+(?=])")
    fun Long.toAtCC():String{
        return "[CQ:at,qq=$this]"
    }
    fun String.getAt():List<Long>{
         val res =  AT_PATTERN.findAll(this)
        return res.map { it.value.toLong() } .toList()
    }

    fun String.urlToImageCode():String{
        return "[CQ:image,file=${this},id=40000]"
    }
}