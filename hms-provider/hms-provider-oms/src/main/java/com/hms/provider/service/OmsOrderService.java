package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.dto.OrderDto;
import com.hms.provider.model.vo.OrderVo;

/**
 * @author luoshao
 * @date 2019/6/1 23:11
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface OmsOrderService extends IService {

    OrderVo[] queryOrderInfo(String userid);

    String createOrderInfo(OrderDto orderDto) throws Exception;

    boolean commitOrder(int order_id,int room_id,String room_name,String mobile);
}
