package com.hms.provider.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hms.provider.dto.CodeDto;

/**
 * @author luoshao
 * @date 2019/5/18 20:39
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public class JwtToken {

    public static String getCodeToken(CodeDto codedto) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(codedto.getPhone_num())).sign(Algorithm.HMAC256(String.valueOf(codedto.getPhone_num())));
        return token;
    }
}
