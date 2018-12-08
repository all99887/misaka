package com.misaka.biz.component

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.data.redis.core.HashOperations
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisComponent<T> {

    @Autowired
    @Qualifier("misakaStringRedisTemplate")
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    @Autowired
    private lateinit var hashOperations: HashOperations<String, String, Any>

    @Autowired
    private lateinit var valueOperations: ValueOperations<String, Any>

    fun put(key: String, obj: T) {
        valueOperations.set(key, obj as Any)
    }

    fun get(key: String): T? {
        return valueOperations.get(key) as T?
    }

    /**
     * 向hash结构中添加数据
     * @param hashKey
     * @param key
     * @param obj
     */
    fun hashPut(hashKey: String, key: String, obj: T) {
        hashOperations.put(hashKey, key, obj as Any)
    }

    /**
     * 向hash结构中添加数据 （expire为整个hash的有效期，单位 秒）
     * @param hashKey
     * @param key
     * @param obj
     * @param expire
     */
    fun hashPut(hashKey: String, key: String, obj: T, expire: Int) {
        hashOperations.put(hashKey, key, obj as Any)
        redisTemplate.expire(hashKey, expire.toLong(), TimeUnit.SECONDS)
    }

    /**
     * 向hash结构中添加数据
     * @param hashKey
     * @param map
     */
    fun hashPutAll(hashKey: String, map: Map<String, T>) {
        hashOperations.putAll(hashKey, map)
    }

    /**
     * 从hash结构中获取数据
     * @param hashKey
     * @param key
     * @return
     */
    fun hashGet(hashKey: String, key: String): T? {
        return hashOperations.get(hashKey, key) as T?
    }

    fun expire(key: String, expire: Int) {
        redisTemplate.expire(key, expire.toLong(), TimeUnit.SECONDS)
    }

    fun hasKey(key: String): Boolean {
        return redisTemplate.hasKey(key)
    }

}