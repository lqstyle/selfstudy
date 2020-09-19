package com.example.demo1.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.joda.time.DateTime;

public class DateUtils {

    private static final String format = "yyyy-MM-dd";

    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat sdfCN = new SimpleDateFormat("yyyy年MM月dd日");

    private final static SimpleDateFormat sdfForYMD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 日期格式，年月日时分，例如：20001230 12:00，20080808 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDD_HH_MI = "yyyyMMdd HH:mm";

    /**
     * 日期格式，年月日时分，例如：2000-12-30 12:00，2008-08-08 20:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI = "yyyy-MM-dd HH:mm";


    // ==格式到年月日 时分秒==
    /**
     * 日期格式，年月日时分秒，例如：20001230120000，20080808200808
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISS = "yyyyMMddHHmmss";

    /**
     * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开
     * 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";


    // ==格式到年月日 时分秒 毫秒==
    /**
     * 日期格式，年月日时分秒毫秒，例如：20001230120000123，20080808200808456
     */
    public static final String DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS = "yyyyMMddHHmmssSSS";

    /**
     * 日期格式，年月日，例如：20050630，20080808
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式，年月，年月日用横杠分开 例如：2005-05
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM = "yyyy-MM";

    /**
     * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";


    public final static String DATE_FORMAT_YY_MM_DD = "yyyy-MM-dd";
    public final static String DATE_FORMAT_YY_MM = "yyyy-MM";

    /**一天内的毫秒数*/
    protected static final int millisOfDay = 86399999;

    /**一天内的秒数*/
    protected static final int secondOfDay = 86399;


    /**
     * 获取上个月的今天
     * @return date
     */
    public static Date getPerviousMonthToday(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        return  c.getTime();
    }

    /**
     * 获取起止时间的天数差值
     * @param startTime
     * @param endTime
     * @return
     */
    public static Integer getDays(DateTime startTime, DateTime endTime) {
        if (startTime == null || endTime == null) {
            return 0;
        }
        //计算起止时间的毫秒差值
        long millis = endTime.minusMillis(endTime.getMillisOfDay()).getMillis()
                - startTime.minusMillis(startTime.getMillisOfDay()).getMillis();
        //将毫秒转换成天数
        return (int) (millis / (millisOfDay + 1));
    }

    public static String formatCN(Date date){
        String ret = "";
        if (date != null) {
            ret = sdfCN.format(date);
        }
        return ret;
    }

    public static String format(Date date) {
        String ret = "";
        if (date != null) {
            ret = sdf.format(date);
        }
        return ret;
    }

    public static String nyrFormat(Date date) {
        String ret = "";
        if (date != null) {
            ret = sdfForYMD.format(date);
        }
        return ret;
    }

    public static String formatByYMD(Date date) {
        if (date != null) {
            return new DateTime(date).toString(format);
        }
        return null;
    }

    public static String formatByYMD(DateTime date) {
        if (date != null) {
            return date.toString(format);
        }
        return null;
    }

    public static boolean lessThanCurrentDay(DateTime date) {
        DateTime currentTime = new DateTime();
        return date.minusMillis(date.getMillisOfDay()).isBefore(currentTime.minusMillis(currentTime.getMillisOfDay()));
    }

    public static Date format(String date) throws Exception {
        Date d11 = sdf.parse(date);
        return d11;
    }

    public static String monthFirstDay(){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar calendar= Calendar.getInstance();
        Date theDate=calendar.getTime();
        GregorianCalendar gcLast=(GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        String day_first=sf.format(gcLast.getTime());
        return day_first;
    }

    public static Date monthFirstDayDate() throws Exception {
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        Calendar calendar= Calendar.getInstance();
        Date theDate=calendar.getTime();
        GregorianCalendar gcLast=(GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        //设置为第一天
        gcLast.set(Calendar.DAY_OF_MONTH, 1);
        Date day_first=format(sf.format(gcLast.getTime()));
        return day_first;
    }

    public static DateTime lastDayOfMonth(DateTime time) {
        DateTime nextMonth = time.plusMonths(1);
        return nextMonth.minusDays(nextMonth.getDayOfMonth()).plusSeconds(secondOfDay);
    }


    public static String format(Date date, String pattern) {
        String ret = "";
        if (date != null) {
            ret = DateFormatUtils.format(date, pattern);
        }
        return ret;
    }

    public static boolean sameDate(Date d1, Date d2) {
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isEqual(localDate2);
    }

    public static boolean before(Date d1, Date d2) {
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isBefore(localDate2);
    }

    public static boolean after(Date d1, Date d2) {
        LocalDate localDate1 = ZonedDateTime.ofInstant(d1.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate localDate2 = ZonedDateTime.ofInstant(d2.toInstant(), ZoneId.systemDefault()).toLocalDate();
        return localDate1.isAfter(localDate2);
    }

    public static Integer compareByYearMonthDay(Date left, Date right) {
        Date d1 = minDateWithHourMinuteSecond(left);
        Date d2 = minDateWithHourMinuteSecond(right);
        return d1.compareTo(d2);
    }

    public static Date minDateWithHourMinuteSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        // 分
        calendar.set(Calendar.MINUTE, 0);
        // 秒
        calendar.set(Calendar.SECOND, 0);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static Date maxDateWithHourMinuteSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 时
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        // 分
        calendar.set(Calendar.MINUTE, 59);
        // 秒
        calendar.set(Calendar.SECOND, 59);
        // 毫秒
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某日期的年份
     *
     * @param date
     * @return
     */
    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取某日期的月份
     *
     * @param date
     * @return
     */
    public static Integer getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取某日期的日数
     *
     * @param date
     * @return
     */
    public static Integer getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);// 获取日
        return day;
    }

    /**
     * 格式化Date时间
     *
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat) {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 获取指定日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar c = new GregorianCalendar();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
        return c.getTime();
    }

    public static String getYesterday() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, -24);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 得到指定的某一天，前几天或后几天 days -24 -48 24 48 ....
     * @param hour
     * @return
     */
    public static String getSpecificDay(int hour) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        return dateFormat.format(calendar.getTime());
    }


    public static String getLastMonth() {
        LocalDate today = LocalDate.now();
        today = today.minusMonths(1);
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("yyyy-MM");
        return formatters.format(today);
    }

    /**
     * 格式化String时间
     *
     * @param strTime String类型时间
     * @param timeFromat String类型格式
     * @return
     */
    public static Date parseStrToDate(String strTime, String timeFromat) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            return dateFormat.parse(strTime);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 验证字符串是否为日期
     * 验证格式:YYYYMMDD、YYYY_MM_DD、YYYYMMDDHHMISS、YYYYMMDD_HH_MI、YYYY_MM_DD_HH_MI、YYYYMMDDHHMISSSSS、YYYY_MM_DD_HH_MI_SS
     * @param strTime
     * @return null时返回false;true为日期，false不为日期
     */
    public static boolean validateIsDate(String strTime) {
        if (strTime == null || strTime.trim().length() <= 0)
            return false;

        Date date = null;
        List<String> list = new ArrayList<String>(0);

        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISSSSS);
        list.add(DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDD_HH_MI);
        list.add(DATE_TIME_FORMAT_YYYYMMDDHHMISS);
        list.add(DATE_FORMAT_YYYY_MM_DD);
        //list.add(DATE_FORMAT_YY_MM_DD);
        list.add(DATE_FORMAT_YYYYMMDD);
        //list.add(DATE_FORMAT_YYYY_MM);
        //list.add(DATE_FORMAT_YYYYMM);
        //list.add(DATE_FORMAT_YYYY);

        for (Iterator iter = list.iterator(); iter.hasNext();) {
            String format = (String) iter.next();
            if(strTime.indexOf("-")>0 && format.indexOf("-")<0)
                continue;
            if(strTime.indexOf("-")<0 && format.indexOf("-")>0)
                continue;
            if(strTime.length()>format.length())
                continue;
            date = parseStrToDate(strTime.trim(), format);
            if (date != null)
                break;
        }

        if (date != null) {
            return true;
        }
        return false;
    }

    /**
     * 两个时间之间相差距离多少月
     *
     * @param dateStr1 时间参数 1：
     * @param dateStr2 时间参数 2：
     * @return 相差月数
     */
    public static int getDistanceMonths(String dateStr1, String dateStr2) throws Exception {
        DateFormat sdf = new SimpleDateFormat(DATE_TIME_FORMAT_YYYY_MM);
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        try {
            bef.setTime(sdf.parse(dateStr1));
            aft.setTime(sdf.parse(dateStr2));
        } catch (ParseException e) {
            return 0;
        }
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        return Math.abs(month + result);
    }

     //获取下个月同一天
     public static String getNextMonthDay(String datestr) {
         SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         java.sql.Date olddate = null;
         try {
             df.setLenient(false);
             olddate = new java.sql.Date(df.parse(datestr).getTime());
         } catch (Exception e) {
             throw new RuntimeException("日期转换错误");
         }
         Calendar cal = new GregorianCalendar();
         cal.setTime(olddate);

         int Year = cal.get(Calendar.YEAR);
         int Month = cal.get(Calendar.MONTH);
         int Day = cal.get(Calendar.DAY_OF_MONTH);


         cal.set(Calendar.YEAR, Year);
         cal.set(Calendar.MONTH, Month+1);
         cal.set(Calendar.DAY_OF_MONTH, Day);

         return new java.sql.Date(cal.getTimeInMillis()).toString();
     }

   //获取下个月第一天
    public static String getFirstDayOfNextMonth(String dateStr){
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH,1);
            calendar.add(Calendar.MONTH, 1);
            return sdf.format(calendar.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d1 = "2017-08-03 12:00:00";
        String d2 = "2017-08-02 23:00:00";
        Date d11 = sdf.parse(d1);
        Date d22 = sdf.parse(d2);

        System.out.println(DateUtils.monthFirstDayDate());

        DateTime now = DateTime.now();
        Date todayStartTime = now.withTimeAtStartOfDay().toDate();
        Date todayEndTime = now.millisOfDay().withMaximumValue().toDate();
        Date monthStartTime = now.monthOfYear().withMinimumValue().toDate();
        Date monthEndTime = now.monthOfYear().withMaximumValue().toDate();

        System.out.println(now.millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.dayOfMonth().withMinimumValue().millisOfDay().withMinimumValue().toString("yyyy-MM-dd HH:mm:ss"));
        System.out.println(now.dayOfMonth().withMaximumValue().millisOfDay().withMaximumValue().toString("yyyy-MM-dd HH:mm:ss"));

//        selectedDev.getBeginDate()).minusDays(1).millisOfDay().withMaximumValue().toDate()
//        System.out.println(DateUtils.before(d11, d22));
//        System.out.println(DateUtils.sameDate(d11, d22));
//        System.out.println(DateUtils.after(d11, d22));


        System.out.println(getOneDayAndEndDayOfMonth("2019-12-28","2020-01-18"));
    }

    /**
     * 根据两个时间获取 其期间所有月的1号和最后一号(包括两个开头和结尾时间)
     * @param minDate 如：2013-11-20
     * @param maxDate 如：2015-05-20
     * @return
     * @throws ParseException
     */
    public static List<String> getOneDayAndEndDayOfMonth(String minDate, String maxDate){
         List<String> result = new ArrayList<String>();
         if(getMonth(DateUtils.getDate(minDate)).equals(getMonth(DateUtils.getDate(maxDate)))){
             result.add(minDate+","+maxDate);
             return result;
         }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//格式化为年月
            Calendar min = Calendar.getInstance();
            Calendar max = Calendar.getInstance();
            min.setTime(sdf.parse(minDate));
            min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
            max.setTime(sdf.parse(maxDate));
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 1);
            Calendar curr = min;
            int i = 0;
            while (curr.before(max)||curr.equals(max)) {
                if(i == 0){
                    result.add(minDate+","+sdf.format(getLastDayOfMonth(curr.getTime())));
                    i++;
                    curr.add(Calendar.MONTH, 1);
                    continue;
                }
                System.out.println(sdf.format(curr.getTime())+"#"+sdf.format(max.getTime()));
                if(curr.equals(max)){
                    result.add(sdf.format(curr.getTime())+","+maxDate);
                }else{
                    result.add(sdf.format(curr.getTime())+","+sdf.format(getLastDayOfMonth(curr.getTime())));
                }

                curr.add(Calendar.MONTH, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 判断两个日期是否同一天
     * @param day1
     * @param day2
     * @return
     */
    public static Boolean isSameDay(Date day1, Date day2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String ds1 = sdf.format(day1);
        String ds2 = sdf.format(day2);
        if (ds1.equals(ds2)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date getDate(String dateTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    //获取两个日期相差多少天
    public static Integer getBetweenDays(Date beginDate, Date endDate) {
        Long betweenDays = (beginDate.getTime() - endDate.getTime()) / (1000L * 3600L * 24L);
        return betweenDays.intValue();
    }
}
