package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/29 16:50
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class DynamicDto implements Serializable {

    private static final long serialVersionUID = 2166714096282826169L;

    private int hotelid;

    private String type;

    private String value;

    public DynamicDto(int hotelid, String type, String value) {
        this.hotelid = hotelid;
        this.type = type;
        this.value = value;
    }
}
