package com.misaka.common.enums

/**
 * @Author: lyx
 * @Date: 2018/10/2 21:24
 * @Version 1.0
 */
enum class ResultCode(val code: Int, val info: String = "") {
    RESULT_OK(200),
    RESULT_ERROR(500, "服务发生错误，请稍后再试"),
    RESULT_PARAMS_ERROR(10001, "传入参数格式错误，请检查传入参数"),
    ERROR_TOKEN(10002, "用户令牌错误"),
    ERROR_USER_INFO(10003, "用户信息错误")
}
