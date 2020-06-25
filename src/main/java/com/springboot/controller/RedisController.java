package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisCluster;

/**
 * @author zhaolei
 * @date 2020-06-08 11:14
 */
@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private JedisCluster jedisCluster;

    @GetMapping("/{name}")
    @ResponseBody
    public String setKey(@PathVariable(value = "name") String name){
        jedisCluster.set("username",name);
        return "缓存成功";
    }

    @GetMapping("/name")
    @ResponseBody
    public String getKey(){
        return jedisCluster.get("username");
    }

}
