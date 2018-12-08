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

fun UserRegisterDto.toDo(): UserDo {
    val userDo = UserDo()
    userDo.userName = this.userName
    userDo.password = this.password
    userDo.lastLoginIp = this.lastLoginIp
    return userDo
}

data class UserRegisterRes(
        val result: Int
){
    companion object {
        const val USER_EXIST_ERROR:Int = 10
    }
}

data class UserLoginDto(
        var userName: String = "",
        var password: String = ""
)

fun UserLoginDto.toDo(): UserDo {
    val userDo = UserDo()
    userDo.userName = this.userName
    userDo.password = this.password
    return userDo
}

data class UserLoginRes(
        val result: Int,
        val user: UserDo?
){
    companion object {
        const val USER_INFO_ERROR:Int = 10
        const val USER_STAUTS_ERROR:Int = 11
    }
}