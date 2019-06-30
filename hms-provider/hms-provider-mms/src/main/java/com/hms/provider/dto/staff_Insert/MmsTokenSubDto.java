package com.hms.provider.dto.staff_Insert;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MmsTokenSubDto")
public class MmsTokenSubDto {

    private static final long serialVersionUID = 8050352918615199254L;

    MmsTokenDto mmsTokenDto;

    private float salary;

    private String position;

    private String enddate;

    private int seniority;           //工龄

    public MmsTokenSubDto(MmsTokenDto mmsTokenDto,float salary,String position,String enddate,int seniority){
        this.mmsTokenDto = mmsTokenDto;
        this.salary = salary;
        this.position = position;
        this.enddate = enddate;
        this.seniority = seniority;
    }
}
