package com.misaka.web.model.vo.request

import com.misaka.common.model.dto.UserRegisterDto
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 * 登录参数封装
 * @Author: lyx
 * @Date: 2018/10/2 21:03
 * @Version 1.0
 */

data class LoginVo(
        @field:NotEmpty
        @field:Size(max = 20)
        var username: String,

        @field:NotEmpty
        @field:Size(max = 32)
        var password: String,

        @field:NotEmpty
        @field:Size(max = 4)
        var captcha: String
)

data class RegisterVo(
        @field:NotEmpty
        @field:Size(max = 20)
        var username: String,

        @field:NotEmpty
        @field:Size(max = 32)
        var password: String

)

fun RegisterVo.toRegisterDto(): UserRegisterDto {
        return UserRegisterDto(this.username, this.password)
}