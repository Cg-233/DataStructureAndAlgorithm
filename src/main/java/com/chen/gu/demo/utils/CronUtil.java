package com.chen.gu.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.apache.juli.logging.Log;
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.TriggerBuilder;

import org.springframework.scheduling.support.CronSequenceGenerator;


public class CronUtil {

    private static final String TRANS_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String TRANS_CRON_FORMAT_ONCE = "ss mm HH dd MM ? yyyy";

    private static final String TRANS_CRON_FORMAT_DAY = "ss mm HH dd/ * ?";

    private static final String TRANS_CRON_FORMAT_WEEK = "ss mm HH ? * -- *";

    private static final String TRANS_CRON_FORMAT_MONTH = "ss mm HH dd MM/1 ? *";

    public static void main(String[] args) throws Exception {
        String result = getCron("day", "2018-08-11 12:11:00");
//       String result = getCron("MON", "2018-08-11 12:11:00");
        // String result = getCronByOnce("2017-01-01 12:12:12");
//       String result = getCron("month", "2019-01-01 12:00:00");
        // String result = getCronToDate("12 12 12 01 01/1 ? 2018");

        System.out.println(result);
        CronSequenceGenerator generator = new CronSequenceGenerator(result);
        Date nextExecutionDate = generator.next(new Date());
        List<String> cronSchdule = getCronSchdule(result, 5);
        System.out.println(cronSchdule);

        Date date = getDate(result);
        System.out.println(date.toString());

    }

    /**
     * 生成只执行一次的cron
     */
    public static String getCronByOnce(String dateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date parse = format.parse(dateStr);
        return fmtDateToStr(parse, TRANS_CRON_FORMAT_ONCE);
    }

    /**
     * 生成每月或每周或每天执行的cron
     */
    public static String getCron(String period, String startDateStr) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat(TRANS_DATE_FORMAT);
        Date startDate = format.parse(startDateStr);
        StringBuffer cronStringBuffer = new StringBuffer();
        if ("month".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_MONTH).trim();
            cronStringBuffer.append(startDateCron);
        } else if ("day".equals(period)) {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_DAY).trim();
            // StringBuffer stringBuffer = new StringBuffer(str);
            // stringBuffer.insert(stringBuffer.lastIndexOf("/") + 1, period);
            // cron = stringBuffer.toString().trim();
            // createPeriod(arrStart, arrEnd, 4);
            cronStringBuffer.append(startDateCron).insert(cronStringBuffer.lastIndexOf("/") + 1, "1");
        } else {
            String startDateCron = fmtDateToStr(startDate, TRANS_CRON_FORMAT_WEEK).trim();
            cronStringBuffer.append(startDateCron.replaceAll("--", period));
        }
        return cronStringBuffer.toString();
    }


    public static String getCronToDate(String cron) {
        String format = null;
        StringBuffer stringBuffer = new StringBuffer(cron);
        int lastIndexOf = stringBuffer.lastIndexOf("/");
        stringBuffer.deleteCharAt(lastIndexOf);
        stringBuffer.deleteCharAt(lastIndexOf);
        String dateFormat = "ss mm HH dd MM ? yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        try {
            Date date = sdf.parse(stringBuffer.toString());
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format = sdf.format(date);
        } catch (Exception e) {
            return null;
        }
        return format;
    }

    /**
     * 格式转换 日期-字符串 
     */
    public static String fmtDateToStr(Date date, String dtFormat) {
        if (date == null) { return ""; }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(dtFormat);
            return dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }






    private static final String DATEFORMAT = "ss mm HH dd MM ? yyyy";

    /***
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /**
     * @param cron
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    public static Date parseStringToDate(String cron, String dateFormat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = null;
        if (cron != null) {
            date = sdf.parse(cron);
        }
        return date;
    }

    /***
     * convert Date to cron ,eg.  "0 06 10 15 1 ? 2014"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(Date date) {
        return formatDateByPattern(date, DATEFORMAT);
    }

    /***
     * convert cron to Date
     * @param cron  : cron表达式 cron表达式仅限于周为*
     * @return
     */
    public static Date getDate(String cron) throws ParseException {
        return parseStringToDate(cron, DATEFORMAT);
    }






    public static List<String> getCronSchdule(String cron, int count){
        List<String> retList = new ArrayList<String>();
        if(!CronExpression.isValidExpression(cron)){
            //Cron表达式不正确
            return retList;
        }
        try {
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("Caclulate Date").withSchedule(
                CronScheduleBuilder.cronSchedule(cron)).build();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = trigger.getStartTime();
            for (int i = 0; i < count; i++) {
                Date time = trigger.getFireTimeAfter(startTime);
                retList.add(format.format(time ));
                startTime = time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retList;
    }
}