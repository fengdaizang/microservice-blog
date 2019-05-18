package com.fdzang.microservice.blog.common.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author tanghu
 * @Date: 2019/1/21 14:08
 */
public class TimeUtils {

    public static LocalDate getByTimeStamp(Long timeStamp){
        Date date=new Date(timeStamp);
        Instant instant=date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate=instant.atZone(zoneId).toLocalDate();

        return localDate;
    }

    public static Long getTimeStamp(Integer year,Integer month){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date=null;
        if(month<10){
            date=year+"-0"+month+"-01 00:00:00";
        }else{
            date=year+"-"+month+"-01 00:00:00";
        }
        LocalDateTime time=LocalDateTime.parse(date,df);
        Timestamp timestamp = Timestamp.valueOf(time);
        return timestamp.getTime();
    }

    synchronized public static String getTimestamp(){
        return System.currentTimeMillis()+"";
    }
}
