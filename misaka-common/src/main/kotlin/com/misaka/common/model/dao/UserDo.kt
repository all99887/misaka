package com.misaka.common.model.dao

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
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
        var lastLoginTime: LocalDateTime? = null,
        var realName: String? = null,
        var lastLoginIp: String? = null
) {
}