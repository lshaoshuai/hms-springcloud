import com.hms.UmsServiceApplication;
import com.hms.provider.service.EmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author luoshao
 * @date 2019/6/5 10:19
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes= UmsServiceApplication.class)
public class EmailTest {

    @Autowired
    private EmailService mailService;

//    @Autowired
//    private TemplateEngine templateEngine;
    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("1939125539@qq.com","测试邮件"," 你好");
    }
}
