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

	private static final long serialVersionUID = 6754402470518968274L;

	private long id;

	private String phone_num;

	private String user_name;

	private String user_id;

	private String email;

	private String accesstoken;
}
