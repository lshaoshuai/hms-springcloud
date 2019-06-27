package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/26 13:46
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class EmptyRoomVo implements Serializable {

    private static final long serialVersionUID = -7332058336264265361L;

    private int pageNum;

    private int total;

    private String searchType;

    private List<LocalRoomVo> emptyRoomList;

    private List<String> roomtypelist;

    public EmptyRoomVo(int pageNum,int total,String searchType,List<LocalRoomVo> roomlist,List<String> roomtypelist){
        this.pageNum = pageNum;
        this.total = total;
        this.searchType = searchType;
        this.emptyRoomList = new ArrayList<>(roomlist);
        this.roomtypelist = new ArrayList<>(roomtypelist);
    }

}
