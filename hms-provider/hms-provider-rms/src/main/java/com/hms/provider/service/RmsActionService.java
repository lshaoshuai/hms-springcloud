package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.vo.RoomVo;

/**
 * @author luoshao
 * @date 2019/6/4 10:23
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface RmsActionService extends IService {

    RoomVo[] getRoomsInfo(int hotel_id);
}
