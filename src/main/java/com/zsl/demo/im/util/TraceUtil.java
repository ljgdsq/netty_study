package com.zsl.demo.im.util;


public class TraceUtil {
    private TraceUtil(){}

    public static void printMethodName(){
      String methodName=  Thread.currentThread().getStackTrace()[2].getMethodName();
        System.out.println(methodName);
    }
}
