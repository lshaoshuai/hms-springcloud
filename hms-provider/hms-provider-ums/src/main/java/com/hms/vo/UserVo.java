package com.hms.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    private long id;

    private long phone_num;

    private String user_name;


}
