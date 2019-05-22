package com.hms;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * The class Redis key util.
 *
 * @author paascloud.net@gmail.com
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class 	RedisKeyUtil {



	private static final String VERIFY_CODE = "hms:verifycode";


	public static String getVerifyCodeKey(String code) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(code), "非法请求code参数不存在");
		return code;
	}

	public static String getAccessTokenKey(String token) {
		Preconditions.checkArgument(StringUtils.isNotEmpty(token), "非法请求token参数不存在");
		return token;
	}


}
