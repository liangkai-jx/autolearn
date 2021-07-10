package com.learn.utlis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author CY小鱼儿
 * @Date 2021/4/29 18:55
 * 时间格式转换
 **/
public class Timedata {
    public static String Stringconversion(Date date){
        SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String a=dateformat.format(date);

        return a;
    }
    public static Date hqdate(){
        Date date1= new Date();
        return date1;
    }
}
