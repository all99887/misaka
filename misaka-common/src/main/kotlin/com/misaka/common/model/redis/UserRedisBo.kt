package com.misaka.common.model.redis

import com.misaka.common.model.dao.UserDo

data class UserRedisBo(
        var userId : Long? = null,
        var userName: String? = null,
        var realName: String? = null
)

fun UserRedisBo.toDo(): UserDo {
    val userDo = UserDo()
    userDo.userId = this.userId
    userDo.userName = this.userName
    userDo.realName = this.realName
    return userDo
}