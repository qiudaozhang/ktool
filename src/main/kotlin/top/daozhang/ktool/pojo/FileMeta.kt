package top.daozhang.ktool.pojo

import java.time.LocalDateTime

class FileMeta {
    var name: String? = null
    var createTime: LocalDateTime? = null
    var updateTime: LocalDateTime? = null
    var size: Long? = null
    override fun toString(): String {
        return "FileMeta(name=$name, createTime=$createTime, updateTime=$updateTime, size=$size)"
    }


}
