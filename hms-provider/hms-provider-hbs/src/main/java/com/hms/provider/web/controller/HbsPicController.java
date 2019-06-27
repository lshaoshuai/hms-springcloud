package com.hms.provider.web.controller;

import com.hms.wrapper.WrapMapper;
import com.hms.wrapper.Wrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author luoshao
 * @date 2019/6/9 10:49
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
@RequestMapping(value = "/hotel")
public class HbsPicController {

    @PostMapping(value = "pic")
    @ApiOperation(httpMethod = "POST", value = "上传图片")
    public Wrapper picUpLoad(@RequestParam("file") MultipartFile file){
        return WrapMapper.ok();
    }
}
