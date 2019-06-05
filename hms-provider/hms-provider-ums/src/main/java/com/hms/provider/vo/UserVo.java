package com.hms.provider.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/***
 * <p>vo(View Object): 视图对象，用于展示层，它的
 * 作用是把某个指定页面(或组件)的所有数据封装起来</p>
 *
 * <p> 用户发出请求（可能是填写表单），表单的数据在展示
 * 层被匹配为VO </p>
 *
 * @author lshao
 */
@Data
@ApiModel(value = "UserVo")
public class UserVo implements Serializable {

    private static final long serialVersionUID = -6249397911566315813L;

    private long id;

    private long phone_num;

    private String user_name;


}
