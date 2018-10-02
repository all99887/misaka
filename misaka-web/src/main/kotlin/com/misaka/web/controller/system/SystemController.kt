package com.misaka.web.controller.system

import com.misaka.common.exception.ParamsFormatException
import com.misaka.web.model.vo.request.LoginVo
import com.misaka.web.model.vo.response.ResultModel
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

    @PostMapping("/login")
    fun login(@Valid loginVo : LoginVo, bindingResult:BindingResult): ResponseEntity<ResultModel> {
        if (bindingResult.hasErrors()){
            throw ParamsFormatException("数据格式错误")
        }

        println(loginVo)

        return ResultModel(null).ok()
    }


}