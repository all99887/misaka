package com.misaka.biz.service

import com.misaka.common.model.dto.UserLoginDto
import com.misaka.common.model.dto.UserLoginRes
import com.misaka.common.model.dto.UserRegisterDto
import com.misaka.common.model.dto.UserRegisterRes

/**
 * @Author: lyx
 * @Date: 2018/10/3 20:39
 * @Version 1.0
 */
interface IUserService {

    fun login(user: UserLoginDto): UserLoginRes

    fun registerUser(user: UserRegisterDto): UserRegisterRes

}