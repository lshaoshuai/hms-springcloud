package com.hms.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The class Uac user token dto.
 *
 * @author paascloud.net @gmail.com
 */
//@EqualsAndHashCode(callSuper = true)
@Data
public class UserTokenDto implements Serializable {
	private static final long serialVersionUID = 3136723742371575367L;

	private long id;

	private long phone_num;

	private String user_token;
}
