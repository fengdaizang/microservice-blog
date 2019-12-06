package com.fdzang.microservice.blog.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

    /**
     * 通过时间戳得到LocalDate
     * @param timeStamp
     * @return
     */
    public static LocalDate getByTimeStamp(Long timeStamp){
        Date date=new Date(timeStamp);
        Instant instant=date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate=instant.atZone(zoneId).toLocalDate();

        return localDate;
    }

    /**
     * 得到某年某月第一天的时间戳
     * @param year
     * @param month
     * @return
     */
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

    /**
     * 得到当前月份的存档
     * @return
     */
    public static Long getCurrentArchivedate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        String date=sdf.format(new Date());
        date+="-01 00:00:00";

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time=LocalDateTime.parse(date,df);
        Timestamp timestamp = Timestamp.valueOf(time);
        return timestamp.getTime();
    }

    /**
     * 得到当前时间戳，加上同步锁，用于生成id
     * @return
     */
    synchronized public static String getTimestamp(){
        return String.valueOf(System.currentTimeMillis());
    }

    /**
     * 得到文章默认链接
     * @return
     */
    public static String getArticleLink(){
        String link="/article/";

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        String date=sdf.format(new Date());

        link+=date+"/"+getTimestamp()+".html";


        return link;
    }
}
