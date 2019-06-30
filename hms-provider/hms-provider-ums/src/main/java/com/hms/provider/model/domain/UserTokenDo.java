package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/29 11:38
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Entity(name = "user_token_info")
@Table(name = "user_token_info") //映射的表名称
@Data
public class UserTokenDo implements Serializable {
    private static final long serialVersionUID = -4830647082785907579L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "phone_num")
    private String phone_num;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_token")
    private String user_token;
}
