package com.qtu.user_service.controller;

import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: lws
 * @create: 2019-11-28 20:34
 * @description:
 */

public class CalanderController {

    public String getYear() {
        Calendar cal = Calendar.getInstance();

        int year = cal.get(Calendar.YEAR);
        return year+"";
    }
    public String getMonth() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH)+1;
        return month+"";
    }
    public String getDay() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day+"";
    }
}
