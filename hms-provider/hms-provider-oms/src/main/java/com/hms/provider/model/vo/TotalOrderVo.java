package com.hms.provider.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luoshao
 * @date 2019/6/26 23:04
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@Data
public class TotalOrderVo implements Serializable {

    private static final long serialVersionUID = -8741936917225829541L;

    private int pageNum;

    private int pageSize;

    private int total;

    private List<LocalOrderVo> list;

    public TotalOrderVo(int pageNum,int pageSize,int total,List<LocalOrderVo> list){

        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.total = total;
        this.list = new ArrayList<>(list);
    }

}
