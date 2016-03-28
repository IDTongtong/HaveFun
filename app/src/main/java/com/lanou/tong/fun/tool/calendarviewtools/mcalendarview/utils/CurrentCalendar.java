package com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.utils;


import com.lanou.tong.fun.tool.calendarviewtools.mcalendarview.vo.DateData;

import java.util.Calendar;


/**
 * Created by bob.sun on 15/8/27.
 */
public class CurrentCalendar {
    public static DateData getCurrentDateData(){
        Calendar calendar = Calendar.getInstance();
        return new DateData(calendar.get(calendar.YEAR), calendar.get(calendar.MONTH) + 1, calendar.get(calendar.DAY_OF_MONTH));
    }

}
