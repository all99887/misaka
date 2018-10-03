package com.misaka.common.utils

import cn.hutool.crypto.digest.DigestAlgorithm
import cn.hutool.crypto.digest.Digester


/**
 * @Author: lyx
 * @Date: 2018/10/3 22:08
 * @Version 1.0
 */
class PwdUtil {

    companion object {
        private val sha256 = Digester(DigestAlgorithm.SHA256)

        private const val salt = "你指尖跃动的电光,是我此生不灭的信仰"

        fun pwdHash(pwd: String): String {
            return sha256.digestHex(pwd + salt)
        }

        fun pwdCheck(pwdDb: String, pwd: String): Boolean {
            return pwdDb == sha256.digestHex(pwd + salt)
        }
    }

}