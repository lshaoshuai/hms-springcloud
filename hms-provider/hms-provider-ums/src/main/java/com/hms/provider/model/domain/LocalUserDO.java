package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/24 11:28
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@Table(name = "local_user_info")
@Entity(name = "local_user_info" )
public class LocalUserDO implements Serializable {

    private static final long serialVersionUID = 1735188846488677828L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "role")
    private int role;

}
