package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 21:58
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = -2446127263671843974L;

    private String userId;

    private String username;

    private String phone;

}
