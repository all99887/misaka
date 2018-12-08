package com.misaka.web.controller.system

import cn.hutool.extra.servlet.ServletUtil
import com.misaka.biz.service.IUserService
import com.misaka.common.consts.SUCCESS
import com.misaka.common.model.dto.UserRegisterRes
import com.misaka.web.model.vo.request.RegisterReq
import com.misaka.web.model.vo.request.toDto
import com.misaka.web.model.vo.response.ResultModel
import io.swagger.annotations.Api
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiParam
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest

@Api("用户相关相关的api")
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: IUserService

    @PostMapping("/register")
    fun register(@ModelAttribute @Validated registerReq: RegisterReq, request: HttpServletRequest): ResponseEntity<ResultModel> {

        val registerDto = registerReq.toDto()
        registerDto.lastLoginIp = ServletUtil.getClientIP(request)
        val result = userService.registerUser(registerDto)

        return when (result.result) {
            SUCCESS -> ResultModel(null).ok()
            UserRegisterRes.USER_EXIST_ERROR -> ResultModel(null).info("该用户名已存在")
            else -> ResultModel(null).ok()
        }
    }

}