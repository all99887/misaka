package com.misaka.common.model.dto

import com.misaka.common.model.dao.UserDo

/**
 * @Author: lyx
 * @Date: 2018/10/3 20:45
 * @Version 1.0
 */

data class UserRegisterDto(
        var userName: String = "",
        var password: String = "",
        var lastLoginIp: String = ""

)

fun UserRegisterDto.toUserDo(): UserDo {
    val userDo = UserDo()
    userDo.userName = this.userName
    userDo.password = this.password
    userDo.lastLoginIp = this.lastLoginIp
    return userDo
}