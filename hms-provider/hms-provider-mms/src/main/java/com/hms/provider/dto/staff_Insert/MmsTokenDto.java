package com.hms.provider.dto.staff_Insert;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "MmsTokenDto")
public class MmsTokenDto {

    private static final long serialVersionUID = 8050352918615199254L;

    private String ssn;

    private String name;

    private String sex;

    private String bdate;

    private String email;

    private String phone;

    private String address;

    private String startdate;

    private String department;
}
