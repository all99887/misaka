package com.misaka.web.model.vo.response

import com.misaka.common.enums.ResultCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

/**
 * 统一的返回
 * @Author: lyx
 * @Date: 2018/10/2 20:45
 * @Version 1.0
 */
class ResultModel @JvmOverloads constructor(val data: Any? = null) {
    var code: Int? = null
        private set
    var info: String? = null
        private set

    private fun createRespone(rsp : ResultModel) = ResponseEntity(rsp, HttpStatus.OK)

    fun send(err: ResultCode): ResponseEntity<ResultModel> {
        this.code = err.code
        this.info = err.info
        return createRespone(this)
    }

    fun send(code: Int, info: String?) : ResponseEntity<ResultModel> {
        this.code = code
        this.info = info
        return createRespone(this)
    }

    fun ok() = send(ResultCode.RESULT_OK)
    fun info(info: String) = send(code = ResultCode.RESULT_OK.code, info = info)
}
