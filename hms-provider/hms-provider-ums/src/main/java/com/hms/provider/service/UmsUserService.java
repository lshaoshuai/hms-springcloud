package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.dto.CodeDto;
import com.hms.provider.model.vo.UserTokenVo;
import com.hms.provider.model.vo.UserVo;

/**
 * @author luoshao
 * @date 2019/5/18 20:00
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface UmsUserService extends IService<UserVo> {

    UserTokenVo createUserToken(CodeDto codedto);

    int createVerifyCode(String mobile);

    boolean queryVerifyCode(long mobile);

}
