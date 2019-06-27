package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 21:27
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class StatusRoomDto implements Serializable {

    private static final long serialVersionUID = 273303047400432878L;

    private int pageNum;

    private int pageSize;

    private String type;

    private String value;

    private int status;
}
