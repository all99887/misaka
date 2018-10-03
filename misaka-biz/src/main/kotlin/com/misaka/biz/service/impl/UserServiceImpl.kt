package com.misaka.biz.service.impl

import com.misaka.biz.mapper.UserMapper
import com.misaka.biz.service.IUserService
import com.misaka.common.component.IdGeneratorComponent
import com.misaka.common.consts.USER_STATUS_ACTIVE
import com.misaka.common.model.dto.UserRegisterDto
import com.misaka.common.model.dto.toUserDo
import com.misaka.common.utils.PwdUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * @Author: lyx
 * @Date: 2018/10/3 20:40
 * @Version 1.0
 */
@Service
@Transactional
class UserServiceImpl : IUserService {

    @Autowired
    lateinit var userMapper: UserMapper

    @Autowired
    lateinit var idGeneratorComponent: IdGeneratorComponent

    override fun registerUser(user: UserRegisterDto) {
        val userDo = user.toUserDo()
        val now = LocalDateTime.now()
        userDo.userId = idGeneratorComponent.genSnowflakeId()
        userDo.password = PwdUtil.pwdHash(userDo.password ?: "")
        userDo.lastLoginIp = ""
        userDo.lastLoginTime = now
        userDo.createTime = now
        userDo.status = USER_STATUS_ACTIVE
        userDo.email = ""
        userDo.phone = ""
        userDo.realName = ""
        userMapper.insert(userDo)
    }
}