package com.hms.base.constant;

/**
 * @author luoshao
 * @date 2019/6/4 9:43
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public class MqConstant {

    /** 订单交换机*/
    public static String ORDER_EXCHANGE = "order-exchange";

    /** 订单交换机*/
    public static String DELAY_ORDER_EXCHANGE = "delay-order-exchange";

    /** 订单交换机*/
    public static String CODE_EXCHANGE = "code-exchange";

    /** 订单交换机*/
    public static String ROOM_EXCHANGE = "room-exchange";

    /*对列名称*/
    public static final String ORDER_QUEUE = "order-queue";

    public static final String CODE_QUEUE = "code-queue";

    public static final String ROOM_QUEUE = "room-queue";

    public static final String DELAY_ORDER_QUEUE = "delay-order-queue";

    public static final String ORDER_ROUTING_KEY = "order_routing_key";

    public static final String CODE_ROUTING_KEY = "code_routing_key";

    public static final String ROOM_ROUTING_KEY = "room_routing_key";

    public static final String DELAY_ORDER_ROUTING_KEY = "delay_order_routing_key";
}
