package com.hms.provider.service;

import com.hms.core.support.IService;
import com.hms.provider.model.dto.CheckDto;
import com.hms.provider.model.dto.OrderFrontDto;
import com.hms.provider.model.dto.SearchOrderDto;
import com.hms.provider.model.vo.LocalOrderDetailVo;
import com.hms.provider.model.vo.TotalOrderVo;

/**
 * @author luoshao
 * @date 2019/6/26 13:52
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public interface OmsLocalService extends IService{

    TotalOrderVo getLimitOrder(int index,int offset);

    TotalOrderVo getOrderByDynamic(SearchOrderDto searchOrderDto);

    LocalOrderDetailVo getOrderInfo(String orderid);

    boolean frontCheckIn(OrderFrontDto orderFrontDto);

    boolean checkOrder(CheckDto checkDto);

}
