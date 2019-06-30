package com.hms.provider.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.core.support.BaseService;
import com.hms.provider.dao.LocalRoomDao;
import com.hms.provider.model.domain.LocalRoomDo;
import com.hms.provider.model.domain.RoomInfo;
import com.hms.provider.model.dto.LocalRoomDto;
import com.hms.provider.model.dto.SearchRoomDto;
import com.hms.provider.model.dto.StatusRoomDto;
import com.hms.provider.model.vo.*;
import com.hms.provider.service.HbsFeignApi;
import com.hms.provider.service.RmsActionService;
import com.hms.provider.service.RmsFeignApi;
import com.hms.provider.service.RmsLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/24 13:55
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Service
public class RmsLocalServiceImpl extends BaseService implements RmsLocalService {

    @Autowired
    private LocalRoomDao localRoomDao;

    @Resource
    private RmsActionService rmsActionService;

    @Resource
    private HbsFeignApi hbsFeignApi;

    @Resource
    private RmsFeignApi rmsFeignApi;

    @Override
    public HotelFloorVo getHotelFloorInfo(int hotelid){
        List<FloorVo> floorlist = new ArrayList<>();
        int floor = (int)hbsFeignApi.getFloorById(hotelid).getResult();
        logger.info("获取到的楼层数为：{}", floor);
        List<LocalRoomDo> roomInfoList = localRoomDao.queryLocalRoomInfo(hotelid);

        for(int i = 1 ; i <= floor ; i++){
            List<LocalRoomVo> list = new ArrayList<>();
            for(LocalRoomDo localRoomDo : roomInfoList){
                if (localRoomDo.getFloor() == i){
                    list.add(new LocalRoomVo(localRoomDo.getId(),localRoomDo.getRoom_no(),localRoomDo.getRoom_status(),localRoomDo.getRoom_type(),localRoomDo.getPrice()));
                }
            }
            floorlist.add(new FloorVo(i,list));
        }

        return new HotelFloorVo(250,floorlist);
    }

    @Override
    public List<LocalRoomVo> getLimitRoom(int index, int offset) {

        List<LocalRoomDo>  localRoomDos = localRoomDao.queryLocalRoomInfoByLimit((index - 1) * offset,offset);
        List<LocalRoomVo> localRoomVos = new ArrayList<>();
        for (LocalRoomDo localRoomDo : localRoomDos){
            localRoomVos.add(new LocalRoomVo(localRoomDo.getId(),localRoomDo.getRoom_no(),localRoomDo.getRoom_status(),localRoomDo.getRoom_type(),200));
        }
        return localRoomVos;
    }

    @Override
    public boolean addRoomInfo(LocalRoomDto localRoomDto){

        LocalRoomDo localRoomDo = new LocalRoomDo();
        localRoomDo.setFloor(localRoomDto.getRoomLocate());
        localRoomDo.setId(localRoomDto.getRoomId());
        localRoomDo.setHotel_id(1);
        localRoomDo.setRoom_no(localRoomDto.getRoomNum());
        localRoomDo.setRoom_status(localRoomDo.getRoom_status());
        localRoomDo.setRoom_type(localRoomDto.getRoomType());
        localRoomDo.setRoom_type_id(1);
        localRoomDo.setPrice(localRoomDto.getRoomPrice());
        int line = localRoomDao.insertOrUpdateRoomInfo(localRoomDo);
        if(line >= 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateRoomInfo(LocalRoomDto localRoomDto){

        LocalRoomDo localRoomDo = new LocalRoomDo();
        localRoomDo.setId(localRoomDto.getRoomId());
        localRoomDo.setFloor(localRoomDto.getRoomLocate());
        localRoomDo.setHotel_id(1);
        localRoomDo.setRoom_no(localRoomDto.getRoomNum());
        localRoomDo.setRoom_status(localRoomDo.getRoom_status());
        localRoomDo.setRoom_type(localRoomDto.getRoomType());
        localRoomDo.setRoom_type_id(1);
        int line = localRoomDao.updateLocalRoomInfoById(localRoomDo);
        if(line >= 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<LocalRoomVo> getRoomByDynamic(SearchRoomDto searchRoomDto){

        return switchRoomType(searchRoomDto);
    }

    /***
     * 通过ID查询ROOMINFO业务层
     * @param id
     * @return
     */
    @Override
    public LocalDetailVo getRoomById(int id){

        LocalRoomDo localRoomDo = localRoomDao.queryLocalRoomInfoById(id);

        RoomInfo roomInfo = new ObjectMapper().convertValue(rmsFeignApi.getRoomTypeInfo(localRoomDo.getRoom_type_id()).getResult(), RoomInfo.class);

        return new LocalDetailVo(
                localRoomDo.getId(),
                localRoomDo.getRoom_no(),
                localRoomDo.getRoom_status(),
                roomInfo.getRoom_name(),
                roomInfo.getRoom_price(),
                1,
                localRoomDo.getFloor(),
                1
        );
    }

    /**
     * 删除ROOMINFO业务层
     * @param id
     * @return
     */
    public boolean removeRoomInfo(int id){

        int line = localRoomDao.deleteRoomInfoById(id);
        if(line >= 0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public EmptyRoomVo getEmptyRoomByDynamic(StatusRoomDto statusRoomDto){

        List<LocalRoomVo> localRoomVos = new ArrayList<>();
        List<LocalRoomDo> localRoomDos = localRoomDao.queryLocalEmptyRoomByDynamic(statusRoomDto);
        for (LocalRoomDo localRoomDo : localRoomDos){
            localRoomVos.add(new LocalRoomVo(localRoomDo.getId(),localRoomDo.getRoom_no(),localRoomDo.getRoom_status(),localRoomDo.getRoom_type(),200));
        }
        return new EmptyRoomVo(
                statusRoomDto.getPageNum(),
                localRoomDao.countDynamicRoomTotal(1,statusRoomDto.getType(),statusRoomDto.getValue(),statusRoomDto.getStatus()),
                "all",
                localRoomVos,
                rmsActionService.roomTypeList(1)
        );
    }

    public List<LocalRoomVo> switchRoomType(SearchRoomDto searchRoomDto){

        List<LocalRoomVo> localRoomVos = new ArrayList<>();
        List<LocalRoomDo> localRoomDos = localRoomDao.queryLocalRoomInfoByDynamic(searchRoomDto);
        for (LocalRoomDo localRoomDo : localRoomDos){
            localRoomVos.add(new LocalRoomVo(localRoomDo.getId(),localRoomDo.getRoom_no(),localRoomDo.getRoom_status(),localRoomDo.getRoom_type(),200));
        }
        return localRoomVos;
    }
}
