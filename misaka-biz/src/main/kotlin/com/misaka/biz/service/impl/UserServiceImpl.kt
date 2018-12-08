package com.misaka.biz.service.impl

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.misaka.biz.mapper.UserMapper
import com.misaka.biz.service.IUserService
import com.misaka.common.component.IdGeneratorComponent
import com.misaka.common.consts.SUCCESS
import com.misaka.common.consts.USER_STATUS_ACTIVE
import com.misaka.common.model.dao.UserDo
import com.misaka.common.model.dto.*
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

    override fun login(user: UserLoginDto): UserLoginRes {
        val queryDo = UserDo(userName = user.userName)
        val query = QueryWrapper(queryDo)
        val userDo = userMapper.selectOne(query)
            ?: return UserLoginRes(UserLoginRes.USER_INFO_ERROR, null)

        if (!PwdUtil.pwdCheck(userDo.password ?: "", user.password)) {
            return UserLoginRes(UserLoginRes.USER_INFO_ERROR, null)
        }
        if (userDo.status != USER_STATUS_ACTIVE) {
            return UserLoginRes(UserLoginRes.USER_STAUTS_ERROR, null)
        }

        return UserLoginRes(SUCCESS, userDo)
    }

    override fun registerUser(user: UserRegisterDto): UserRegisterRes {
        val userDo = user.toDo()

        val query = QueryWrapper(UserDo(userName = userDo.userName))
        var userDoDb = userMapper.selectOne(query)
        if (userDoDb != null) {
            return UserRegisterRes(UserRegisterRes.USER_EXIST_ERROR)
        }

        val now = LocalDateTime.now()
        userDo.userId = idGeneratorComponent.genSnowflakeId()
        userDo.password = PwdUtil.pwdHash(userDo.password ?: "")
        userDo.lastLoginIp = ""
        userDo.lastLoginTime = now
        userDo.createTime = now
        userDo.updateTime = now
        userDo.status = USER_STATUS_ACTIVE
        userDo.email = ""
        userDo.phone = ""
        userDo.realName = ""
        userMapper.insert(userDo)
        return UserRegisterRes(SUCCESS)
    }
}