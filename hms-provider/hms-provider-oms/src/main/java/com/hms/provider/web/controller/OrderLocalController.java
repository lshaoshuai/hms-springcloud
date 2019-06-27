    package com.hms.provider.web.controller;

    import com.hms.annotation.NoNeedAccessAuthentication;
    import com.hms.core.support.BaseController;
    import com.hms.provider.model.dto.CheckDto;
    import com.hms.provider.model.dto.OrderFrontDto;
    import com.hms.provider.model.dto.SearchOrderDto;
    import com.hms.provider.model.vo.LocalOrderDetailVo;
    import com.hms.provider.model.vo.TotalOrderVo;
    import com.hms.provider.service.OmsLocalService;
    import com.hms.wrapper.WrapMapper;
    import com.hms.wrapper.Wrapper;
    import io.swagger.annotations.Api;
    import io.swagger.annotations.ApiImplicitParam;
    import io.swagger.annotations.ApiImplicitParams;
    import io.swagger.annotations.ApiOperation;
    import org.springframework.http.MediaType;
    import org.springframework.web.bind.annotation.*;

    import javax.annotation.Resource;

    /**
     * @author luoshao
     * @date 2019/6/24 16:57
     * @projectname HMS
     * @github https://github.com/lshaoshuai/hms-springcloud
     */
    @RestController
    @RequestMapping(value = "/local")
    @Api(description = "PMS订单信息处理接口",value = "WEB - OmsOrderController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public class OrderLocalController extends BaseController {


        @Resource
        OmsLocalService omsLocalService;

        @PostMapping("/commit")
        @ApiOperation(httpMethod = "POST", value = "前台开房")
        @NoNeedAccessAuthentication
        public Wrapper commitOrderForm(@RequestBody OrderFrontDto orderFrontDto) {

            logger.info("获取到的前台订单信息： {}" , orderFrontDto);
            boolean is_success = omsLocalService.frontCheckIn(orderFrontDto);
            if(is_success){
                return WrapMapper.ok("入住成功");
            }else {
                return WrapMapper.ok("入住失败");
            }

        }

        @PostMapping("/limit/{pagenum}/{pagesize}")
        @ApiOperation(httpMethod = "POST", value = "查询订单分页信息")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "pagenum", value = "索引位置", required = true, dataType = "int", paramType = "path"),
                @ApiImplicitParam(name = "pagesize", value = "位移数", required = true, dataType = "int", paramType = "path")
        })
        @NoNeedAccessAuthentication
        public Wrapper queryLoaclOrderList(@PathVariable int pagenum,@PathVariable int pagesize) {
            logger.info("获取到查询请求信息 :{},{}", pagenum, pagesize);
            TotalOrderVo totalOrderVo = omsLocalService.getLimitOrder(pagenum,pagesize);
            return WrapMapper.ok(totalOrderVo);
        }

        @PostMapping("/list")
        @ApiOperation(httpMethod = "POST", value = "动态查询订单列表")
        @NoNeedAccessAuthentication
        public Wrapper searchRoomList(@RequestBody SearchOrderDto searchOrderDto) {

            logger.info("获取到查询请求信息 = {}", searchOrderDto);
            TotalOrderVo totalOrderVo = omsLocalService.getOrderByDynamic(searchOrderDto);
            return WrapMapper.ok(totalOrderVo);
        }

        @PostMapping("/order/detail/{orderid}")
        @ApiOperation(httpMethod = "POST", value = "通过订单ID查询订单信息")
        @NoNeedAccessAuthentication
        public Wrapper queryOrderInfo(@PathVariable String orderid) {

            logger.info("获取到订单ID = {}", orderid);
            LocalOrderDetailVo localOrderDetailVo = omsLocalService.getOrderInfo(orderid);
            return WrapMapper.ok(localOrderDetailVo);
        }

        @PostMapping("/checkin")
        @ApiOperation(httpMethod = "POST", value = "顾客入住")
        @NoNeedAccessAuthentication
        public Wrapper checkOutRoom(@RequestBody CheckDto checkDto) {

            logger.info("用户退房信息获取 = {}", checkDto);
            boolean is_success = omsLocalService.checkOrder(checkDto);
            if(is_success){
                return WrapMapper.ok("入住成功");
            }else {
                return WrapMapper.ok("入住失败");
            }

        }

        @PostMapping("/checkout")
        @ApiOperation(httpMethod = "POST", value = "顾客退房")
        @NoNeedAccessAuthentication
        public Wrapper checkinRoom(@RequestBody CheckDto checkDto) {

            logger.info("用户入住信息获取 = {}", checkDto);
            boolean is_success = omsLocalService.checkOrder(checkDto);
            if(is_success){
                return WrapMapper.ok("退房成功");
            }else {
                return WrapMapper.ok("退房失败");
            }

        }

    }
