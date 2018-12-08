package com.misaka.web.model.vo.request

import com.misaka.common.model.dto.UserLoginDto
import com.misaka.common.model.dto.UserRegisterDto
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

/**
 * 登录参数封装
 * @Author: lyx
 * @Date: 2018/10/2 21:03
 * @Version 1.0
 */
@ApiModel(value="用户登录信息")
data class LoginReq(
        @ApiModelProperty(value = "用户名", required = true)
        @field:NotEmpty
        @field:Size(max = 20)
        var username: String,

        @ApiModelProperty(value = "密码", required = true)
        @field:NotEmpty
        @field:Size(max = 32)
        var password: String,

        @ApiModelProperty(value = "验证码", required = true)
        @field:NotEmpty
        @field:Size(max = 4)
        var captcha: String
)

fun LoginReq.toDto(): UserLoginDto {
        return UserLoginDto(this.username, this.password)
}

@ApiModel(value="用户注册信息")
data class RegisterReq(
        @ApiModelProperty(value = "密码", required = true)
        @field:NotEmpty
        @field:Size(max = 20)
        var username: String,

        @ApiModelProperty(value = "验证码", required = true)
        @field:NotEmpty
        @field:Size(max = 32)
        var password: String

)

fun RegisterReq.toDto(): UserRegisterDto {
        return UserRegisterDto(this.username, this.password)
}