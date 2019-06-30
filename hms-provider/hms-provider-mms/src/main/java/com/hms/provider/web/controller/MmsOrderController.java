package com.hms.provider.web.controller;

import com.hms.core.support.BaseController;
import com.hms.provider.Service.MyService;
import com.hms.provider.dto.SelectCom;
import com.hms.provider.dto.Update.MmsRSubUpdateDto;
import com.hms.provider.dto.people_select.webTransformDto;
import com.hms.provider.dto.staff_Insert.MmsTokenDto;
import com.hms.provider.vo.ManagerVo;
import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MmsOrderController extends BaseController {

    @Resource
    MyService myservice;

    @PostMapping("/test1-1/{pageNum}")
    @ApiOperation(httpMethod = "POST",value = "人事全部查询")
    public Wrapper myController(@PathVariable int pageNum){
        webTransformDto webtransformdto = myservice.selectAll(pageNum);
        if(webtransformdto==null){
            return WrapMapper.error();
        }
        else{
            return WrapMapper.ok(webtransformdto);
        }
    }


    @PostMapping("/test1-2")
    @ApiOperation(httpMethod = "POST",value = "人事id查询")
    public Wrapper myController1(@RequestBody int id){
        ManagerVo managerVo = myservice.selectIdAll(id);
        if(managerVo==null){
            return WrapMapper.error();
        }
        else{
            return WrapMapper.ok(managerVo);
        }
    }

    @PostMapping("/test1-3")
    @ApiOperation(httpMethod = "POST",value = "人事name查询")
    public Wrapper myController1(@RequestBody SelectCom selectCom){
        webTransformDto webtransformdtoName = myservice.selectNameAll(selectCom.getName(),selectCom.getPageNum());
        if(webtransformdtoName==null){
            return WrapMapper.error();
        }
        else{
            return WrapMapper.ok(webtransformdtoName);
        }
    }


    @PostMapping("/test2")
    @ApiOperation(httpMethod ="POST",value = "人员录用")
    public Wrapper myInsertControllerr(@RequestBody MmsTokenDto mmsTokenDto) {
        String s = myservice.createManager(mmsTokenDto);
        if (s == null) {
            return WrapMapper.error();
        } else {
            return WrapMapper.ok(s);
        }
    }

    @PostMapping("/test3")
    @ApiOperation(httpMethod ="POST",value = "人事修改")
    public Wrapper myUpdateControllerr(MmsRSubUpdateDto mmsrSubUpdateDto, int id) {
        String s3 = myservice.rUpdateManager(mmsrSubUpdateDto, id);
        if (s3 == null) {
            return WrapMapper.error();
        } else {
            return WrapMapper.ok(s3);
        }
    }
}
