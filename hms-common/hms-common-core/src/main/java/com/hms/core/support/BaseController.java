package com.hms.core.support;

import com.hms.PublicUtil;
import com.hms.ThreadLocalMap;
import com.hms.base.constant.GlobalConstant;
import com.hms.base.dto.LocalUserTokenDto;
import com.hms.base.dto.UserTokenDto;
import com.hms.base.enums.ErrorCodeEnum;
import com.hms.base.exception.BusinessException;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luoshao
 * @date 2019/5/16 10:17
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public class BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Gets login auth dto.
     *
     * @return the login auth dto
     */
    protected UserTokenDto getLoginAuthDto() {
        UserTokenDto userTokenDto =  (UserTokenDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (PublicUtil.isEmpty(userTokenDto)) {
            throw new BusinessException(ErrorCodeEnum.UAC10011041);
        }
        return userTokenDto;
    }

    /**
     * LocalUserToken
     * @return
     */
    protected LocalUserTokenDto getLocalLoginAuthDto() {

        LocalUserTokenDto localUserTokenDto =  (LocalUserTokenDto) ThreadLocalMap.get(GlobalConstant.Sys.TOKEN_AUTH_DTO);
        if (PublicUtil.isEmpty(localUserTokenDto)) {
            throw new BusinessException(ErrorCodeEnum.UAC10011041);
        }
        return localUserTokenDto;
    }

    /**
     * Handle result wrapper.
     *
     * @param <T>    the type parameter
     * @param result the result
     *
     * @return the wrapper
     */
    protected <T> Wrapper<T> handleResult(T result) {
        boolean flag = isFlag(result);

        if (flag) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "操作成功", result);
        } else {
            return WrapMapper.wrap(Wrapper.ERROR_CODE, "操作失败", result);
        }
    }

    /**
     * Handle result wrapper.
     *
     * @param <E>      the type parameter
     * @param result   the result
     * @param errorMsg the error msg
     *
     * @return the wrapper
     */
    protected <E> Wrapper<E> handleResult(E result, String errorMsg) {
        boolean flag = isFlag(result);

        if (flag) {
            return WrapMapper.wrap(Wrapper.SUCCESS_CODE, "操作成功", result);
        } else {
            return WrapMapper.wrap(Wrapper.ERROR_CODE, errorMsg, result);
        }
    }

    private boolean isFlag(Object result) {
        boolean flag;
        if (result instanceof Integer) {
            flag = (Integer) result > 0;
        } else if (result instanceof Boolean) {
            flag = (Boolean) result;
        } else {
            flag = PublicUtil.isNotEmpty(result);
        }
        return flag;
    }
}
