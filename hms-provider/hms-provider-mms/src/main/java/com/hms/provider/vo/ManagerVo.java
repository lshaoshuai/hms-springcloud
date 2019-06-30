package com.hms.provider.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "ManagerVo")
public class ManagerVo implements Serializable {
    private static final long serialVersionUID = 7371899636828413473L;

    private int id;

    private String ssn;

    private String name;

    private String sex;

    private String bdate;

    private String email;

    private String phone;

    private String address;

    private float salary;

    private String position;

    private String startdate;

    private String enddate;

    private String department;

    private int seniority;      //工龄
}
