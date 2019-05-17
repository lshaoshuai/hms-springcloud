import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import per.luo.service.room.RoomServiceApplication;
import per.luo.service.room.redis.RedisService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/5/15 10:42
 * @projectname HMS
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RoomServiceApplication.class)
public class RedisTest{

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisService redisService;

    @Test
    public void test() throws Exception {
        if(redisService.exists("qq")){
            System.out.println("have");
        }

        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("v");
        stringRedisTemplate.opsForValue().set("abc", "测试");
        stringRedisTemplate.opsForList().leftPushAll("qq", list); // 向redis存入List
        stringRedisTemplate.opsForList().range("qwe", 0, -1).forEach(value -> {
            System.out.println(value);
        });
    }
}