package com.misaka.common.model.dao

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.misaka.common.model.redis.UserRedisBo
import java.time.LocalDateTime

/**
 * @Author: lyx
 * @Date: 2018/10/3 20:02
 * @Version 1.0
 */
@TableName("user")
data class UserDo(
        @TableId(type = IdType.INPUT)
        var userId: Long? = null,
        var userName: String? = null,
        var password: String? = null,
        var phone: String? = null,
        var email: String? = null,
        var status: Int? = null,
        var createTime: LocalDateTime? = null,
        var updateTime: LocalDateTime? = null,
        var lastLoginTime: LocalDateTime? = null,
        var realName: String? = null,
        var lastLoginIp: String? = null
)

fun UserDo.toRedisBo():UserRedisBo{
        val userRedisBo = UserRedisBo()
        userRedisBo.userId = this.userId
        userRedisBo.userName = this.userName
        userRedisBo.realName = this.realName
        return userRedisBo
}