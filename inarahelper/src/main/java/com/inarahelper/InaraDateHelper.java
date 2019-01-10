package com.inarahelper;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by teddy on 1/16/17.
 */

public class InaraDateHelper {

    public static final String DATE_INPUT_v1 = "yyyy-MM-dd";
    public static final String DATE_INPUT_v2 = "yyyy-MM-dd HH:mm:ss";
    //sample 01 jan 2017
    public static String DATE_OUTPUT_v1 = "dd MMM yyyy";
    //sample  01 January 2007
    public static final String DATE_OUTPUT_v2 = "dd MMMM yyyy";
    //Monday, 01 Jan 2007
    public static final String DATE_OUTPUT_v3 = "EEEE, dd MMMM yyyy";
    public static final String DATE_OUTPUT_v4= "EEEE, dd MMM yyyy";


    /**
     *
     * @param inputFormat InaraDateHelper.DATE_INPUT_v1
     * @param outputFormat InaraDateHelper.DATE_OUTPUT_v4
     * @param inputDate 2007-01-01
     * @return Monday, 01 Jan 2007
     */
    public static String formateDateFromstring(String inputFormat, String outputFormat, String inputDate) {
        Date parsed = null;
        String outputDate = "";
        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, Locale.getDefault());
        SimpleDateFormat df_output = new SimpleDateFormat(outputFormat, Locale.getDefault());

        try {
            parsed = df_input.parse(inputDate);
            outputDate = df_output.format(parsed);

        } catch (ParseException e) {
        }
        return outputDate;

    }

    /**
     *
     * @param dateInput sample "2007-01-01"
     * @param old_format sample InaraDateHelper.DATE_INPUT_v1
     * @param formatDate sample InaraDateHelper.DATE_OUTPUT_v3
     * @return Senin, 01 Januari 2007
     */
    public static String dateParseToString(String dateInput, String old_format, String formatDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(old_format);
            Date date;
            date = sdf.parse(dateInput);
            Locale id = new Locale("in", "ID");
            SimpleDateFormat format = new SimpleDateFormat(formatDate, id);
            String result = format.format(date);
            return result;
        } catch (ParseException e) {
            return dateInput;
        }
    }

    public static String dateParseToString(Date date, String old_format){
        Locale id = new Locale("in", "ID");
        SimpleDateFormat format = new SimpleDateFormat(old_format, id);
        String result = format.format(date);
        return result;
    }

    public static Date dateParseToDate(String dateInput, String old_format){
        try {
            Locale id = new Locale("in", "ID");
            SimpleDateFormat sdf = new SimpleDateFormat(old_format,id);
            Date date = sdf.parse(dateInput);
            return date;
        } catch (Exception e) {
            return null;
        }
    }


    /*
     * @return WeekOfMonth
    */
    public static int getWeekOfMonth() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.WEEK_OF_MONTH);
        return week - 1;
    }


    /*
    * @return DayOfWeek
    */
    public static int getDayOfWeek() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        if (day == 1) {
            day = 7;
        } else {
            day = day - 1;
        }
        return day;
    }
    
    public static int getAge(int year, int month, int day) {
         Calendar dob = Calendar.getInstance();
         Calendar today = Calendar.getInstance();
         dob.set(year, month, day);
         int age = today.get(1) - dob.get(1);
         if(today.get(6) < dob.get(6)) {
            --age;
         }

         return age;
    }

    public static int getYear() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }


    public static int getMonth() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.MONTH);
    }

    public static int getDay() {
        Calendar calendar = GregorianCalendar.getInstance();
        return calendar.get(Calendar.DATE);
    }


    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format, Locale.getDefault());
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public static String getDateTime(@NonNull SimpleDateFormat simpleDateFormat) {
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    @Deprecated
    public static String millisecond2String(Object millisecond, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return simpleDateFormat.format(millisecond);
    }

    public static int minuteToMilli(int minute) {
        return minute * 60 * 1000;
    }

    public static int milliToMinute(int milli) {
        return milli / 60 / 1000;
    }

    public static String getDisplayTime(String datetime, @NonNull SimpleDateFormat simpleDateFormat) {
        Date date = parse(simpleDateFormat, datetime);
        SimpleDateFormat formatter =  new SimpleDateFormat(
                getDefaultDisplayTimeFormat(), Locale.getDefault());
        return formatter.format(date).replace("am", "AM").replace("pm", "PM");
    }


    @Nullable
    public static Date parse(SimpleDateFormat simpleDateFormat, String datetime) {
        try {
            return simpleDateFormat.parse(datetime);
        } catch(Exception e){
            Log.e("TimeHelper", Log.getStackTraceString(e));
            return null;
        }
    }

    private static String getDefaultDisplayTimeFormat() {
        return "hh:mm a";
    }

}


