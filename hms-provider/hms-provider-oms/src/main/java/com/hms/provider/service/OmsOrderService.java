package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.dto.OrderDto;

/**
 * @author luoshao
 * @date 2019/6/1 23:11
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface OmsOrderService extends IService {

    OrderDto queryOrderInfo();

    void sendOrderInfoToMq(int roomid ,long userid);
}
