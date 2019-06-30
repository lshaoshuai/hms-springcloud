package com.hms.provider.dto.Update;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MmsRSubUpdateDto")
public class MmsRSubUpdateDto {

    private String position;

    private String enddate;

    private String department;

    private float salary;
}
