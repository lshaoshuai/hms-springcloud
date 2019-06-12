package com.hms.provider.service;

/**
 * @author luoshao
 * @date 2019/6/5 9:46
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface EmailService {

    void sendSimpleMail(String to, String subject, String content);
}
