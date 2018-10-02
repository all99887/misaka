package com.misaka.web.model.vo.request

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
        var username:String,

        @field:NotEmpty
        @field:Size(max = 32)
        var password:String,

        @field:NotEmpty
        @field:Size(max = 4)
        var captcha:String
)