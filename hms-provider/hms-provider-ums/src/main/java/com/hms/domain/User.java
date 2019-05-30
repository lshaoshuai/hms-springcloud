package com.hms.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author luoshao
 * @date 2019/5/30 22:27
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */

/***
 *  <p>
 *      DO(Domain Object):领域对象，就是从现实世界中抽象出来的有形或无形的业务实体。
 *      对应数据库中存储的数据表
 *  </p>
 */
@Entity(name = "user")
@Table(name = "T_STUDENT") //映射的表名称
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer stuNum;


    @Column(length = 20)
    private String stuName;
}
