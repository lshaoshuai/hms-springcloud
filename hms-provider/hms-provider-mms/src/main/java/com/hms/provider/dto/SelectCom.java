package com.hms.provider.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("SelectCom")
public class SelectCom {

    private String name;

    private int pageNum;
}
