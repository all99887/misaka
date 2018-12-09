package com.misaka.web.controller.system

import cn.hutool.core.lang.UUID
import cn.hutool.extra.servlet.ServletUtil
import com.misaka.biz.component.RedisComponent
import com.misaka.biz.service.IUserService
import com.misaka.common.consts.SUCCESS
import com.misaka.common.consts.USER_REDIS_KEY
import com.misaka.common.model.dao.toRedisBo
import com.misaka.common.model.dto.UserLoginRes
import com.misaka.common.model.dto.UserRegisterRes
import com.misaka.common.model.redis.UserRedisBo
import com.misaka.web.controller.BaseController
import com.misaka.web.model.vo.request.LoginReq
import com.misaka.web.model.vo.request.RegisterReq
import com.misaka.web.model.vo.request.toDto
import com.misaka.web.model.vo.response.ResultModel
import com.misaka.web.model.vo.response.UserVo
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

/**
 * 系统相关操作controller
 * @Author: lyx
 * @Date: 2018/10/2 20:56
 * @Version 1.0
 */

@Api("系统相关相关的api")
@RestController
@RequestMapping("/system")
class SystemController: BaseController(){

    @Autowired
    lateinit var userService: IUserService

    @ApiOperation(value = "登录接口", notes = "登录接口")
    @PostMapping("/login")
    fun login(@ModelAttribute @Validated loginReq : LoginReq, request: HttpServletRequest): ResponseEntity<ResultModel> {

        val loginDto = loginReq.toDto()
        val result = userService.login(loginDto)

        return when (result.result) {
            SUCCESS -> {
                val userRedisBo = result.user?.toRedisBo()
                val token = UUID.randomUUID().toString(true)
                if(userRedisBo != null){
                    userRedis.put(USER_REDIS_KEY + token, userRedisBo)
                }
                ResultModel(UserVo(userRedisBo?.userName?:"", userRedisBo?.realName?:"", token)).ok()
            }
            UserLoginRes.USER_INFO_ERROR -> ResultModel(null).info("用户名或密码错误")
            UserLoginRes.USER_STAUTS_ERROR -> ResultModel(null).info("用户已被停用")
            else -> ResultModel(null).ok()
        }
    }

}