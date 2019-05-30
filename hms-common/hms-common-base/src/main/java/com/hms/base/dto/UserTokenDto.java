package com.hms.base.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The class Uac user token dto.
 *
 * @author paascloud.net @gmail.com
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserTokenDto extends LoginAuthDto {
	private static final long serialVersionUID = 3136723742371575367L;

	private long id;

	private long phone_num;

	private String acesstoken;
}
