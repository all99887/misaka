package com.misaka.web.controller

import com.misaka.common.enums.ResultCode
import com.misaka.common.exception.ParamsFormatException
import com.misaka.web.model.vo.response.ResultModel
import org.slf4j.LoggerFactory
import org.springframework.beans.BeanInstantiationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

/**
 * 全局异常处理
 * @Author: lyx
 * @Date: 2018/10/2 20:43
 * @Version 1.0
 */
@RestControllerAdvice
class ExceptionHandleController {

    private val logger = LoggerFactory.getLogger(ExceptionHandleController::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleParamsFormatException1(e: MethodArgumentNotValidException): ResponseEntity<ResultModel> {
        logger.error("数据格式错误", e)
        return ResultModel(null).send(ResultCode.RESULT_PARAMS_ERROR)
    }

    @ExceptionHandler(ParamsFormatException::class)
    fun handleParamsFormatException2(e: ParamsFormatException): ResponseEntity<ResultModel> {
        logger.error("数据格式错误", e)
        return ResultModel(null).send(ResultCode.RESULT_PARAMS_ERROR)
    }

    @ExceptionHandler(BeanInstantiationException::class)
    fun handleParamsFormatException3(e: IllegalArgumentException): ResponseEntity<ResultModel> {
        logger.error("数据格式错误", e)
        return ResultModel(null).send(ResultCode.RESULT_PARAMS_ERROR)
    }

    @ExceptionHandler(BindException::class)
    fun handleParamsFormatException4(e: BindException): ResponseEntity<ResultModel> {
        logger.error("数据格式错误", e)
        return ResultModel(null).send(ResultCode.RESULT_PARAMS_ERROR)
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handleNotFoundException(e: NoHandlerFoundException) : ResponseEntity<ResultModel> {
        val status = HttpStatus.NOT_FOUND
        return ResultModel().send(status.value(), e.message)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ResultModel> {
        logger.error("后台错误", e)
        return ResultModel(null).send(ResultCode.RESULT_ERROR)
    }

}