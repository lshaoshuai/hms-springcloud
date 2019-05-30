package com.hms.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author luoshao
 * @date 2019/5/30 22:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

@Entity(name = "userinfo")
@Table(name = "user_info") //映射的表名称
@Data
public class UserInfo {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 100)
    private String phone_num;

    @Column(length = 255)
    private String user_name;

    @Column(length = 255)
    private String user_token;

}
