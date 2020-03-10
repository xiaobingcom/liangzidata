package com.sandu.common.config;

import com.sandu.common.exception.RedisInitException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Jedis 配置
 * 用户得到 JedisPool 对象
 * @author xiaobing
 * @date 2020-02-29 16:35
 * @version v1.0.0
 * @Description
 *
 * Modification History:
 * Date                 Author          Version          Description
---------------------------------------------------------------------------------*
 * 2020-02-29 16:35     xiaobing          v1.0.0           Created
 *
 */
@Component
public class JedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.timeout}")
    private int redisTimeout;


    /**
     * 初始化 JedisPool
     * 使用默认配置
     * @return
     */
    @Bean
    public JedisPool getJedisPool() {


        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

            if (StringUtils.isEmpty(this.redisPassword)) {
                return new JedisPool(jedisPoolConfig, this.redisHost, this.redisPort, this.redisTimeout);
            } else {
                return new JedisPool(jedisPoolConfig, this.redisHost, this.redisPort, this.redisTimeout, this.redisPassword);
            }
        } catch (Throwable cause) {
            throw new RedisInitException("Jedis Pool 初始化失败，请检查 Redis 配置是否存在", cause);
        }



    }


}
