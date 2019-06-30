package com.hms.provider.dto.people_select;

import com.hms.provider.vo.ManagerVo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "webTransformDto")
public class webTransformDto {

    private static final long serialVersionUID = 8050352918615199254L;

    private int page;

    private int allMessage;

    private List<ManagerVo> spiritManagerVos;

    public webTransformDto(int page,int allMessage,List<ManagerVo> spiritManagerVos){

        this.page = page;

        this.allMessage = allMessage;

        this.spiritManagerVos = spiritManagerVos;
    }
}
