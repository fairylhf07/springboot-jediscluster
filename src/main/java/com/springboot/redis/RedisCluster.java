package com.springboot.redis;

import com.springboot.properties.RedisProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


import java.util.HashSet;

/**
 * @author zhaolei
 * @date 2020-06-08 09:25
 */
@Configuration
@Slf4j
public class RedisCluster {


    @Autowired
    private RedisProperties redisProperties;


    @Bean
    public JedisCluster jedisCluster(){

        String nodes = redisProperties.getNodes();
        log.info("----------配置文件参数：{}",nodes);
        String[] node = nodes.split(",");
        HashSet<HostAndPort> hostAndPort = new HashSet<>();
        for(String ipPort:node){
            String[] split = ipPort.split(":");
            log.info("--------host:{},port:{}",split[0].trim(),split[1].trim());
            hostAndPort.add(new HostAndPort(split[0].trim(),Integer.valueOf(split[1].trim())));
        }
        // Jedis连接池配置
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        return new JedisCluster(hostAndPort,jedisPoolConfig);


    }
}
