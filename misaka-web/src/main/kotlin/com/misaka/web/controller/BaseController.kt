package com.misaka.web.controller

import com.misaka.biz.component.RedisComponent
import com.misaka.common.consts.HEADER_TOKEN
import com.misaka.common.consts.USER_REDIS_KEY
import com.misaka.common.model.redis.UserRedisBo
import org.springframework.beans.factory.annotation.Autowired
import javax.servlet.http.HttpServletRequest


open class BaseController {

    @Autowired
    protected lateinit var userRedis: RedisComponent<UserRedisBo>

    /**
     * 获取用户信息
     */
    fun getUserInfo(request: HttpServletRequest): UserRedisBo?{
        val token = request.getHeader(HEADER_TOKEN)
        return userRedis.get(USER_REDIS_KEY + token)
    }

    /**
     * 获取用户id
     */
    fun getUserId(request: HttpServletRequest): Long?{
        return getUserInfo(request)?.userId
    }

}