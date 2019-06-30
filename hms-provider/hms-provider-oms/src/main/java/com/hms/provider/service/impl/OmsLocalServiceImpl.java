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
import java.text.SimpleDateFormat;
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

        List<LocalOrderDo> localOrderDos = localOrderDao.queryLocalOrderInfoByLimit((index - 1) * offset ,offset);
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
        return new TotalOrderVo(index,offset,localOrderDao.countOrderTotal(1),localOrderVos);
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
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localOrderDo.getCheck_in()),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(localOrderDo.getCheck_out()),
                localOrderDo.getCheck_in_status(),
                localOrderDo.getMoney()
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

        return new TotalOrderVo(
                searchOrderDto.getPageNum(),searchOrderDto.getPageSize(),localOrderDao.countOrderTotalByDynamic(new DynamicDto(1,searchOrderDto.getType(),searchOrderDto.getValue())),localOrderVos
        );
    }

    /***
     *
     * 前台入住
     * @param orderFrontDto
     * @return
     */
    @Override
    @Transactional
    public boolean frontCheckIn(OrderFrontDto orderFrontDto) {

        LocalOrderDo localOrderDo = new LocalOrderDo();
        localOrderDo.setOrder_id(UUID.randomUUID().toString().replaceAll("-", ""));
        localOrderDo.setCheck_in(orderFrontDto.getCheckIn());
        localOrderDo.setCheck_out(orderFrontDto.getCheckOut());
        localOrderDo.setCheck_in_status(orderFrontDto.getCheckInStatus());
        localOrderDo.setOrigin(orderFrontDto.getOrigin());
        localOrderDo.setRoom_id(orderFrontDto.getRoomId());
        localOrderDo.setUser_id(orderFrontDto.getUserId());
        localOrderDo.setHotel_id(1);
        int price = (int)rmsFeignApi.getRoomPrice(orderFrontDto.getRoomId()).getResult();
        localOrderDo.setMoney(price);
        CustomerDto customerDto = new CustomerDto();
        customerDto.setPhone(orderFrontDto.getPhone());
        customerDto.setUsername(orderFrontDto.getUsername());
        customerDto.setUserId(orderFrontDto.getUserId());
        boolean ums_success = (boolean)umsFeignApi.addCustomerInfo(customerDto).getResult(); ///用户不能重复，更新
        Preconditions.checkArgument(ums_success,"用户信息插入失败");
        logger.info("RoomId:{} CheckInStatus: {}",orderFrontDto.getRoomId(),orderFrontDto.getCheckInStatus());
        boolean rms_success = (boolean)rmsFeignApi.updateLocalRoomInfo(orderFrontDto.getRoomId(),orderFrontDto.getCheckInStatus()).getResult();
        Preconditions.checkArgument(rms_success,"客房信息更新失败");
        localOrderDo.setCount(100);
        int line = localOrderDao.insertOrUpdateOrderInfo(localOrderDo);
        if(line >= 0){
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
        if(line >= 0){
            return true;
        }else{
            return false;
        }
    }

}
