import com.hms.UmsServiceApplication;
import com.hms.provider.service.UmsUserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author luoshao
 * @date 2019/5/19 12:37
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@SpringBootTest(classes= UmsServiceApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MQTest {

    @Autowired
    private UmsUserService helloSender;

}