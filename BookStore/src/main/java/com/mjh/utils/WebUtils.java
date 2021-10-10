package com.mjh.utils;

/**
 * @author mjh
 * @date 2021-10-10 12:07
 */
public class WebUtils {

    public static int parseInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        }catch (Exception e){

        }
        return defaultValue;
    }
}
