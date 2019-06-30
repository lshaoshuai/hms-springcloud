package com.hms.provider.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 写于2019/6/23  黄丹枫
 * 借鉴:https://blog.csdn.net/satan91/article/details/80958925
 */
@Entity(name = "employee")
@Table(name = "employee")
@Data
public class Manager implements Serializable {

    private static final long serialVersionUID = 8050352918615199254L;

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "name")
    private String name;

    @Column(name = "sex")
    private String sex;

    @Column(name = "bdate")
    private String bdate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "salary")
    private float salary;

    @Column(name = "position")
    private String position;

    @Column(name = "startdate")
    private String startdate;

    @Column(name = "enddate")
    private String enddate;

    @Column(name = "department")
    private String department;

    @Column(name = "seniority")
    private int seniority;        //工龄
}
