package com.springboot.cluster;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaolei
 * @date 2020-06-09 10:59
 */
public class Cluster {
    private static JedisCluster jedis;

    /*static {
        // 添加集群的服务节点Set集合
        Set<HostAndPort> hostAndPortsSet = new HashSet<HostAndPort>();
        // 添加节点
        hostAndPortsSet.add(new HostAndPort("192.168.221.131", 6379));
        hostAndPortsSet.add(new HostAndPort("192.168.221.131", 6380));
        hostAndPortsSet.add(new HostAndPort("192.168.221.132", 6379));
        hostAndPortsSet.add(new HostAndPort("192.168.221.132", 6380));
        hostAndPortsSet.add(new HostAndPort("192.168.221.133", 6379));
        hostAndPortsSet.add(new HostAndPort("192.168.221.133", 6380));

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
        jedis = new JedisCluster(hostAndPortsSet, jedisPoolConfig);
    }*/

    /**
     * 测试key:value数据
     * 集群中flushDB、keys废弃
     */
    @Test
    public void testKey() throws InterruptedException {
        //System.out.println("清空数据："+jedis.flushDB());
        System.out.println("判断某个键是否存在：" + jedis.exists("username"));
        System.out.println("新增<'username','wukong'>的键值对：" + jedis.set("username", "xiaohai"));
        System.out.println("是否存在:" + jedis.exists("username"));
        System.out.println("新增<'password','password'>的键值对：" + jedis.set("password", "123456"));
        //Set<String> keys = jedis.keys("*");
        // System.out.println("系统中所有的键如下："+keys);
        System.out.println("删除键password:" + jedis.del("password"));
        System.out.println("判断键password是否存在：" + jedis.exists("password"));
        System.out.println("设置键username的过期时间为10s:" + jedis.expire("username", 10));
        TimeUnit.SECONDS.sleep(2); // 线程睡眠2秒System.out.println("查看键username的剩余生存时间："+jedis.ttl("username"));
        System.out.println("查看键username的剩余生存时间：" + jedis.ttl("username"));
        System.out.println("移除键username的生存时间：" + jedis.persist("username"));
        System.out.println("查看键username的剩余生存时间：" + jedis.ttl("username"));
        System.out.println("查看键username所存储的值的类型：" + jedis.type("username"));
    }

    @Test
    public void testJedis(){
        Jedis jedis = new Jedis("192.168.221.131", 6379);
        jedis.set("name","1112223332121");


    }
}