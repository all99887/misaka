package com.misaka.biz.service

import com.misaka.common.model.dto.UserRegisterDto

/**
 * @Author: lyx
 * @Date: 2018/10/3 20:39
 * @Version 1.0
 */
interface IUserService {

    fun registerUser(user: UserRegisterDto)

}