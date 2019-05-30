package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.dto.CodeDto;
import com.hms.vo.UserVo;

/**
 * @author luoshao
 * @date 2019/5/18 20:00
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface UmsUserService extends IService<UserVo> {

    public void send();

    String createUserToken(CodeDto codedto);

    int createVerifyCode(long mobile);

    boolean queryVerifyCode(long mobile);

}
