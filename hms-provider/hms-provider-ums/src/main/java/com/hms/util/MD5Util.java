package com.hms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author luoshao
 * @date 2019/5/20 10:05
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MD5Util {

    /**
     * Encrypt string.
     *
     * @param password 密码
     *
     * @return the string
     */
    public static String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

    /**
     * 密码是否匹配.
     *
     * @param rawPassword     明文
     * @param encodedPassword 密文
     *
     * @return the boolean
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword) {
        return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
    }

}
