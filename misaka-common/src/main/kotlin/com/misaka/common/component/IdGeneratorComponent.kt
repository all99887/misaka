package com.misaka.common.component

import cn.hutool.core.lang.Snowflake
import cn.hutool.core.util.IdUtil
import com.misaka.common.properties.SnowFlakeProperties
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

/**
 * @Author: lyx
 * @Date: 2018/10/3 21:39
 * @Version 1.0
 */
@Component
class IdGeneratorComponent {

    @Autowired
    private lateinit var snowFlakeProperties: SnowFlakeProperties

    private var snowflake: Snowflake? = null

    @PostConstruct
    fun init() {
        snowflake = IdUtil.createSnowflake(snowFlakeProperties.terminal, snowFlakeProperties.cluster)
    }

    fun genSnowflakeId(): Long {
        return snowflake?.nextId() ?: 0
    }

}