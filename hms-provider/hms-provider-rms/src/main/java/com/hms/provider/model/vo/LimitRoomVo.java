package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/26 10:48
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class LimitRoomVo implements Serializable {
    private static final long serialVersionUID = -3282184075151398469L;

    private int pageNum;

    private int pageSize;

    private int total;

    private List<LocalRoomVo> list;

   public LimitRoomVo(int pageNum,int pageSize,int total, List<LocalRoomVo> list){
       this.pageNum = pageNum;
       this.pageSize = pageSize;
       this.total = total;
       this.list = new ArrayList<>(list);
   }
}
