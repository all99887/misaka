package com.misaka.biz.redis

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.SerializationException
import java.nio.charset.Charset

class FastJson2JsonRedisSerializer<T>(private val clazz: Class<T>) : RedisSerializer<T> {

    @Throws(SerializationException::class)
    override fun serialize(t: T?): ByteArray? {
        return if (t == null) {
            ByteArray(0)
        } else JSON.toJSONString(t, SerializerFeature.WriteClassName).toByteArray(DEFAULT_CHARSET)
    }

    @Throws(SerializationException::class)
    override fun deserialize(bytes: ByteArray?): T? {
        if (bytes == null || bytes.isEmpty()) {
            return null
        }
        val str = String(bytes, DEFAULT_CHARSET)

        return JSON.parseObject(str, clazz) as T
    }

    companion object {
        val DEFAULT_CHARSET = Charset.forName("UTF-8")
    }

}