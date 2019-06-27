package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/5/30 22:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

@Entity(name = "userinfo")
@Table(name = "user_info") //映射的表名称
@Data
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 5203316233473433307L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    private String phone_num;

    @Column(length = 255)
    private String user_name;

    @Column(length = 255)
    private String user_token;

}
