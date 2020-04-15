package com.tt.handsomeman.util;

import java.util.Date;

public class TimeCount {
    public static int getDaysBetween(Date startDate,
                                     Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();

        long diffDays = diff / (24 * 60 * 60 * 1000);

        return (int) diffDays;
    }

    public static int getHoursBetween(Date startDate,
                                      Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();

        long diffHours = diff / (60 * 60 * 1000);

        return (int) diffHours;
    }

    public static int getMinutesBetween(Date startDate,
                                        Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();

        long diffMinutes = diff / (60 * 1000);

        return (int) diffMinutes;
    }

    public static int getSecondsBetween(Date startDate,
                                        Date endDate) {
        long diff = endDate.getTime() - startDate.getTime();

        long diffSecond = diff / 1000;

        return (int) diffSecond;
    }
}
