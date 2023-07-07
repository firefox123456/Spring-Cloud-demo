package hnfnu.zmq;


import hnfnu.zmq.common.redis.RedisDao;
import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

//@SpringBootTest
public class RedisTest {

    @Autowired
    RedisDao redisDao;
    @Autowired
    RedissonClient redissonClient;

    @Test
    public void testRedis() {
        redisDao.setEx("hello","world",60,TimeUnit.SECONDS);
    }

}
