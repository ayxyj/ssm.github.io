package cn.edu.zzu.controller;

import cn.edu.zzu.common.lang.Result;
import cn.edu.zzu.domain.User;
import cn.edu.zzu.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api("用户API")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/save")
    public Result saveUser(@RequestBody User user) {
        stringRedisTemplate.opsForValue().set("1", "test");
        System.out.println(stringRedisTemplate.opsForValue().get("1"));
        redisTemplate.opsForValue().set("2", user);
        System.out.println((User) redisTemplate.opsForValue().get("2"));
        return Result.success(user);
    }

    /**
     * List类型  类似于栈
     *
     * @return
     */
    @GetMapping("/readList")
    public Result redisList() {
        String key = "redis-list-key";
        String value = String.valueOf(System.currentTimeMillis() + 1);
        stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.opsForList().leftPush(key, value);
        stringRedisTemplate.opsForList().leftPush(key, value);
        return Result.success(stringRedisTemplate.opsForList().leftPop(key));
    }

    /**
     * set类型 hashset
     */
    @GetMapping("/readSet")
    public Result readSet() {
        String key = "redis-set-key";
        String value = String.valueOf(System.currentTimeMillis() + 1);
        stringRedisTemplate.opsForSet().add(key, value);
        stringRedisTemplate.opsForSet().add(key, value + "1");
        stringRedisTemplate.opsForSet().add(key, value + "2");
        return Result.success(stringRedisTemplate.opsForSet().pop(key));
    }

    @GetMapping("/readHash")
    public Result readHash() {
        String key = "redis-hash-key";
        String hashkey = String.valueOf(System.currentTimeMillis());
        String value = String.valueOf(System.currentTimeMillis() + 1);
        stringRedisTemplate.opsForHash().put(key, hashkey, value);
        stringRedisTemplate.opsForHash().put(key, hashkey + "1", value + "1");
        stringRedisTemplate.opsForHash().put(key, hashkey + "2", value + "2");
        return Result.success(stringRedisTemplate.opsForHash().get(key , hashkey));
    }

    @GetMapping("/readZSet")
    public Result readZSet(){
        String key = "redis-zset-key" ;
        String  hashkey = String.valueOf(System.currentTimeMillis());
        stringRedisTemplate.opsForZSet().add(key , hashkey , System.currentTimeMillis());
        return Result.success(stringRedisTemplate.opsForZSet().zCard(key));
    }
}
