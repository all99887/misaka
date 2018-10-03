package com.misaka.web.controller.system

import cn.hutool.extra.servlet.ServletUtil
import com.misaka.biz.mapper.UserMapper
import com.misaka.biz.service.IUserService
import com.misaka.common.exception.ParamsFormatException
import com.misaka.web.model.vo.request.LoginVo
import com.misaka.web.model.vo.request.RegisterVo
import com.misaka.web.model.vo.request.toRegisterDto
import com.misaka.web.model.vo.response.ResultModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

/**
 * 系统相关操作controller
 * @Author: lyx
 * @Date: 2018/10/2 20:56
 * @Version 1.0
 */
@RestController
@RequestMapping("/system")
class SystemController {

    @Autowired
    lateinit var userService: IUserService

    @PostMapping("/login")
    fun login(@Valid loginVo : LoginVo, bindingResult:BindingResult): ResponseEntity<ResultModel> {
        if (bindingResult.hasErrors()){
            throw ParamsFormatException("数据格式错误")
        }

        println(loginVo)

        return ResultModel(null).ok()
    }

    @PostMapping("/register")
    fun register(@Valid registerVo: RegisterVo, bindingResult: BindingResult, request: HttpServletRequest): ResponseEntity<ResultModel> {
        if (bindingResult.hasErrors()) {
            throw ParamsFormatException("数据格式错误")
        }

        val registerDto = registerVo.toRegisterDto()
        registerDto.lastLoginIp = ServletUtil.getClientIP(request)
        userService.registerUser(registerDto)

        return ResultModel(null).ok()
    }


}