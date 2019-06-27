package com.hms.provider.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;
import com.hms.core.support.BaseService;
import com.hms.provider.dao.LocalOrderDao;
import com.hms.provider.model.domain.LocalOrderDo;
import com.hms.provider.model.dto.*;
import com.hms.provider.model.vo.CustomVo;
import com.hms.provider.model.vo.LocalOrderDetailVo;
import com.hms.provider.model.vo.LocalOrderVo;
import com.hms.provider.model.vo.TotalOrderVo;
import com.hms.provider.service.OmsLocalService;
import com.hms.provider.service.RmsFeignApi;
import com.hms.provider.service.UmsFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author luoshao
 * @date 2019/6/26 13:53
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class OmsLocalServiceImpl extends BaseService implements OmsLocalService {

    @Autowired
    LocalOrderDao localOrderDao;

    @Resource
    UmsFeignApi umsFeignApi;

    @Resource
    RmsFeignApi rmsFeignApi;

    @Override
    public TotalOrderVo getLimitOrder(int index,int offset) {

        List<LocalOrderDo> localOrderDos = localOrderDao.queryLocalOrderInfoByLimit(index,offset);
        List<LocalOrderVo> localOrderVos = new ArrayList<>();

        for (LocalOrderDo localOrderDo : localOrderDos){
            LocalRoomDto localRoomDto = new ObjectMapper().convertValue(rmsFeignApi.getLocalRoomInfo(localOrderDo.getRoom_id()).getResult(), LocalRoomDto.class);
            CustomVo customVo = new ObjectMapper().convertValue(umsFeignApi.queryCustomerInfo(localOrderDo.getUser_id()).getResult(), CustomVo.class);
            localOrderVos.add(
                    new LocalOrderVo(
                            localOrderDo.getOrder_id(),
                            localRoomDto.getId(),
                            localOrderDo.getUser_id(),
                            customVo.getUsername(),
                            localRoomDto.getRoom_type(),
                            localRoomDto.getRoom_no(),
                            localOrderDo.getOrigin(),
                            localOrderDo.getCheck_in_status()
                            )
            );
        }
        return new TotalOrderVo(1,1,1,localOrderVos);
    }

    @Override
    public LocalOrderDetailVo getOrderInfo(String orderid) {

        LocalOrderDo localOrderDo = localOrderDao.queryLocalOrderInfoByOrderId(orderid);
        LocalRoomDto localRoomDto = new ObjectMapper().convertValue(rmsFeignApi.getLocalRoomInfo(localOrderDo.getRoom_id()).getResult(), LocalRoomDto.class);
        CustomVo customVo = new ObjectMapper().convertValue(umsFeignApi.queryCustomerInfo(localOrderDo.getUser_id()).getResult(), CustomVo.class);
        return new LocalOrderDetailVo(

                localOrderDo.getOrder_id(),
                customVo.getUser_id(),
                customVo.getUsername(),
                customVo.getPhone(),
                localRoomDto.getId(),
                localRoomDto.getRoom_type(),
                localRoomDto.getRoom_no(),
                localRoomDto.getPrice(),
                localOrderDo.getOrigin(),
                localOrderDo.getCheck_in(),
                localOrderDo.getCheck_out(),
                localOrderDo.getCheck_in_status(),
                1000
        );
    }

    @Override
    public TotalOrderVo getOrderByDynamic(SearchOrderDto searchOrderDto) {

        List<LocalOrderDo> localOrderDos = localOrderDao.queryLocalOrderInfoByDynamic(searchOrderDto);
        List<LocalOrderVo> localOrderVos = new ArrayList<>();
        for (LocalOrderDo localOrderDo : localOrderDos){
            LocalRoomDto localRoomDto = new ObjectMapper().convertValue(rmsFeignApi.getLocalRoomInfo(localOrderDo.getRoom_id()).getResult(), LocalRoomDto.class);
            CustomVo customVo = new ObjectMapper().convertValue(umsFeignApi.queryCustomerInfo(localOrderDo.getUser_id()).getResult(), CustomVo.class);
            localOrderVos.add(
                    new LocalOrderVo(
                            localOrderDo.getOrder_id(),
                            localRoomDto.getId(),
                            localOrderDo.getUser_id(),
                            customVo.getUsername(),
                            localRoomDto.getRoom_type(),
                            localRoomDto.getRoom_no(),
                            localOrderDo.getOrigin(),
                            localOrderDo.getCheck_in_status()
                    )
            );
        }
        return new TotalOrderVo(1,1,1,localOrderVos);
    }

    @Override
    @Transactional
    public boolean frontCheckIn(OrderFrontDto orderFrontDto) {

        LocalOrderDo localOrderDo = new LocalOrderDo();
        localOrderDo.setOrder_id(UUID.randomUUID().toString().replaceAll("-", ""));
        localOrderDo.setCheck_in(orderFrontDto.getCheckIn());
        localOrderDo.setCheck_out(orderFrontDto.getCheckOut());
        localOrderDo.setCheck_in_status(orderFrontDto.getCheckInStatus());
        localOrderDo.setOrigin(orderFrontDto.getOrigin());
        localOrderDo.setRoom_id(orderFrontDto.getRoomid());
        localOrderDo.setUser_id(orderFrontDto.getUserid());
        localOrderDo.setHotel_id(1);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setPhone(orderFrontDto.getPhone());
        customerDto.setUsername(orderFrontDto.getUsername());
        customerDto.setUserId(orderFrontDto.getUserid());
        boolean ums_success = (boolean)umsFeignApi.addCustomerInfo(customerDto).getResult();
        Preconditions.checkArgument(ums_success,"用户信息插入失败");
        boolean rms_success = (boolean)rmsFeignApi.updateLocalRoomInfo(orderFrontDto.getRoomid(),3).getResult();
        Preconditions.checkArgument(rms_success,"客房信息更新失败");
        localOrderDo.setCount(100);
        int line = localOrderDao.insertOrUpdateOrderInfo(localOrderDo);
        if(line > 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean checkOrder(CheckDto checkDto){

        boolean is_sucess = (boolean)rmsFeignApi.updateLocalRoomInfo(checkDto.getRoomId(),checkDto.getRoomStatus()).getResult();
        Preconditions.checkArgument(is_sucess,"房间更新失败");
        int line = localOrderDao.updateOrderStatus(checkDto.getCheckInStatus(),checkDto.getOrderId());
        if(line > 0){
            return true;
        }else{
            return false;
        }
    }

}
