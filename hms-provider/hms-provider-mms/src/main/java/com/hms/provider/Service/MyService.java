package com.hms.provider.Service;

import com.hms.core.support.IService;
import com.hms.provider.dto.Update.MmsRSubUpdateDto;
import com.hms.provider.dto.people_select.webTransformDto;
import com.hms.provider.dto.staff_Insert.MmsTokenDto;
import com.hms.provider.vo.ManagerVo;

public interface MyService extends IService {
    /**
     * 查询
     * @param page
     * @return
     */
    webTransformDto selectAll(int page);
    ManagerVo selectIdAll(int id);
    webTransformDto selectNameAll(String name,int page);

    /**
     * 添加
     * @param mmsTokenDto
     * @return
     */
    String createManager(MmsTokenDto mmsTokenDto);


    /**
     * 修改
     * @param id
     * @return
     */
    String rUpdateManager(MmsRSubUpdateDto mmsRSubUpdateDto, int id);
}
