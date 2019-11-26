import com.hms.UmsServiceApplication;
import com.hms.core.support.BaseController;
import com.hms.provider.model.dto.CodeDto;
import com.hms.provider.model.vo.UserTokenVo;
import com.hms.provider.service.UmsUserService;
import org.junit.Test;
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
public class MQTest extends BaseController {

    @Autowired
    private UmsUserService umsUserService;

    @Test
    public void MqTest() throws Exception {


        CodeDto codeDto = new CodeDto();
        codeDto.setPhone_num("18846086270");
        codeDto.setVerifyCode(umsUserService.createVerifyCode("18846086270"));
        UserTokenVo userTokenVo = umsUserService.createUserToken(codeDto);
        logger.info("response: {}", userTokenVo );
    }
}