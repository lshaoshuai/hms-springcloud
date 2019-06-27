package com.hms.provider.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author luoshao
 * @date 2019/6/26 14:23
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class SearchRoomDto implements Serializable {

    private static final long serialVersionUID = -804147868595851784L;

    private int pageNum;

    private int pageSize;

    private String type;

    private String value;

}
