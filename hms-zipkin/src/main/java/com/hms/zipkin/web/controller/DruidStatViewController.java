package com.hms.zipkin.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luoshao
 * @date 2019/6/22 11:12
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
@RestController
public class DruidStatViewController{
        @GetMapping("/druid/stat")
        public Object druidStat(){
                // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
                return null;
        }
}
