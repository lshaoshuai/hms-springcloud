package com.hms.provider.Service.impl;

import com.hms.core.support.BaseService;
import com.hms.provider.Service.MyService;
import com.hms.provider.dao.ManagerDao;
import com.hms.provider.dto.Update.MmsRSubUpdateDto;
import com.hms.provider.dto.people_select.webTransformDto;
import com.hms.provider.dto.staff_Insert.MmsTokenDto;
import com.hms.provider.dto.staff_Insert.MmsTokenSubDto;
import com.hms.provider.vo.ManagerVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyServiceImpl extends BaseService implements MyService {

    @Autowired
    ManagerDao managerDao;

    /**
     * 三种查找--------------------------------------------------------------------------------------
     * @param page
     * @return
     */
    @Override
    public webTransformDto selectAll(int page) {
        List<ManagerVo> spiritManagerVos;
        List<ManagerVo> managerVos = managerDao.querManager();
        webTransformDto webtransformdto;
        int allDocumentCount = managerVos.size();
        int allPage;
        if((managerVos!=null)&&(allDocumentCount-(page-1)*10>=10)){
            spiritManagerVos = managerVos.subList((page-1)*10,(page-1)*10+10);
        }
        else{
            spiritManagerVos = managerVos.subList((page-1)*10,allDocumentCount);
        }
        webtransformdto = new webTransformDto(page,allDocumentCount,spiritManagerVos);
        return  webtransformdto;
    }


    @Override
    public ManagerVo selectIdAll(int id) {
        ManagerVo managerVo = managerDao.querIdManager(id);
        return managerVo;
    }

    @Override
    public webTransformDto selectNameAll(String name,int page) {
        List<ManagerVo> spiritManagerVos;
        List<ManagerVo> managerVos = managerDao.querNameManager(name);
        webTransformDto webtransformdto;
        int allDocumentCount = managerVos.size();
            if((managerVos!=null)&&(allDocumentCount-(page-1)*10>=10)){
            spiritManagerVos = managerVos.subList((page-1)*10,(page-1)*10+10);
        }
            else{
            spiritManagerVos = managerVos.subList((page-1)*10,allDocumentCount);
        }
        webtransformdto = new webTransformDto(page,allDocumentCount,spiritManagerVos);
        return  webtransformdto;
    }


    /**
     * 录入员工--------------------------------------------------------------------------------------
     * @param mmsTokenDto
     * @return
     */
    @Override
    public String createManager(MmsTokenDto mmsTokenDto) {
        MmsTokenSubDto mmsTokenSubDto;
        float salary = 0;
        if(mmsTokenDto.getDepartment().equals("研发部")){
            salary = 8000;
        }else if(mmsTokenDto.getDepartment().equals("市场营销部")){
            salary = 4000;
        }else if(mmsTokenDto.getDepartment().equals("产品推广部")){
            salary = 3000;
        }else if(mmsTokenDto.getDepartment().equals("人力资源部")){
            salary = 2500;
        }else{}
        String position = "员工";
        mmsTokenSubDto = new MmsTokenSubDto(mmsTokenDto,salary,position,"0",0);
        managerDao.insertManager(mmsTokenSubDto);
        return "添加成功";
    }


    /**
     * 修改员工信息---------------------------------------------------------------------------------
     * @param id
     * @return
     */
    @Override
    public String rUpdateManager(MmsRSubUpdateDto mmsRSubUpdateDto, int id) {
        managerDao.updateManager(mmsRSubUpdateDto, id);
        if(mmsRSubUpdateDto.getEnddate().equals("0")){
            return "转岗成功";
        }else{
            return "退休愉快!";
        }
    }

}
