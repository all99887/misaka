package com.misaka.common.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * @Author: lyx
 * @Date: 2018/10/3 21:44
 * @Version 1.0
 */
@Component
@ConfigurationProperties("snowflake.id")
class SnowFlakeProperties {

    var terminal: Long = 0
    var cluster: Long = 0

}