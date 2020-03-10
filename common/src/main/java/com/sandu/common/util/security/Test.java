package com.sandu.common.util.security;

public class Test {


    /**
     * 获取一个 TOKEN  过期时间为 10 天
     * @param args
     */
    public static void main(String[] args) {
        String token = JWTUtil.getInstance().generateToken("1","2",14400 , "meaqua");

        System.out.println(token);
    }



}
