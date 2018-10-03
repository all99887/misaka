package com.misaka.biz.config

import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Configuration

/**
 * @Author: lyx
 * @Date: 2018/10/3 19:45
 * @Version 1.0
 */
@MapperScan("com.misaka.biz.mapper")
@Configuration
class MybatisConfig {


}