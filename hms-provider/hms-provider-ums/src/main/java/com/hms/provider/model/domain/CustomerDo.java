package com.hms.provider.model.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 18:31
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
@Table(name = "customer_info")
@Entity(name = "customer_info" )
public class CustomerDo implements Serializable {


    private static final long serialVersionUID = -6992807905303871635L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;
}
