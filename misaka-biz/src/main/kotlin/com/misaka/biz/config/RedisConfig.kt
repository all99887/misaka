package com.misaka.biz.config

import com.alibaba.fastjson.parser.ParserConfig
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer
import com.misaka.biz.redis.FastJson2JsonRedisSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.*
import org.springframework.data.redis.serializer.StringRedisSerializer
import javax.annotation.PostConstruct

@Configuration
class RedisConfig : CachingConfigurerSupport() {

    @PostConstruct
    fun addAccept() {
        ParserConfig.getGlobalInstance().addAccept("com.misaka.common.model.redis.")
    }

    /**
     * 注入 RedisConnectionFactory
     */
    @Autowired
    lateinit var redisConnectionFactory: RedisConnectionFactory

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean(name = ["misakaStringRedisTemplate"])
    fun functionDomainRedisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory)
        return redisTemplate
    }

    /**
     * 设置数据存入 redis 的序列化方式
     *
     * @param redisTemplate
     * @param factory
     */
    private fun initDomainRedisTemplate(redisTemplate: RedisTemplate<String, Any>, factory: RedisConnectionFactory) {
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = FastJson2JsonRedisSerializer(Any::class.java)
        redisTemplate.valueSerializer = FastJson2JsonRedisSerializer(Any::class.java)
        redisTemplate.setConnectionFactory(factory)
    }

    /**
     * 实例化 HashOperations 对象,可以使用 Hash 类型操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun hashOperations(redisTemplate: RedisTemplate<String, Any>): HashOperations<String, String, Any> {
        return redisTemplate.opsForHash()
    }

    /**
     * 实例化 ValueOperations 对象,可以使用 String 操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun valueOperations(redisTemplate: RedisTemplate<String, Any>): ValueOperations<String, Any> {
        return redisTemplate.opsForValue()
    }

    /**
     * 实例化 ListOperations 对象,可以使用 List 操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun listOperations(redisTemplate: RedisTemplate<String, Any>): ListOperations<String, Any> {
        return redisTemplate.opsForList()
    }

    /**
     * 实例化 SetOperations 对象,可以使用 Set 操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun setOperations(redisTemplate: RedisTemplate<String, Any>): SetOperations<String, Any> {
        return redisTemplate.opsForSet()
    }

    /**
     * 实例化 ZSetOperations 对象,可以使用 ZSet 操作
     *
     * @param redisTemplate
     * @return
     */
    @Bean
    fun zSetOperations(redisTemplate: RedisTemplate<String, Any>): ZSetOperations<String, Any> {
        return redisTemplate.opsForZSet()
    }

}