package com.hms;

/**
 * @author luoshao
 * @date 2019/5/30 22:10
 * @projectname HMS
 * @github https://github.com/lshaoshuai/hms-springcloud
 */
public class RadomUtil {

    public static String createRadomUserName(){
        int num = (int)(Math.random()*9000)+1000;
        return String.valueOf(num);
    }

    public static int createRadomID(){
        int num = (int)(Math.random()*900000)+100000;
        return num;
    }

    public static long createRadomOrderID(){
        int num = (int)(Math.random()*900000)+100000;
        return num;
    }
}
