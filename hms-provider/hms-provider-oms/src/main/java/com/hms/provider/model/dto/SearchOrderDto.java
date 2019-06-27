package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 19:07
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class SearchOrderDto implements Serializable {


    private static final long serialVersionUID = -912985283774829097L;

    private int pageNum;

    private int pageSize;

    private String type;

    private String value;
}
