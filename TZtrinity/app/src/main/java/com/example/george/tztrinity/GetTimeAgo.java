package com.example.george.tztrinity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by George on 09.08.2018.
 */

public class GetTimeAgo {
    private static final int SECOND = 1000;
    private static final int MINUTE = 60 * SECOND;
    private static final int HOUR_MILLIS = 60 * MINUTE;
    private static final int DAY_MILLIS = 24 * HOUR_MILLIS;

    public static String getTimeAgo(long time) {
        if (time < 1000000000000L) {
            // если временная метка задана в секундах, преобразуется в миллисекунды
            time *= 1000;
        }

        long now = System.currentTimeMillis();
        if (time > now || time <= 0) {
            return null;
        }


        final long diff = now - time;
        if (diff < MINUTE) {
            return "только что";
        } else if (diff < 2 * MINUTE) {
            return "минуту назад";
        } else if (diff < 50 * MINUTE) {
            return diff / MINUTE + " минут назад";
        } else if (diff < 90 * MINUTE) {
            return "час назад";
        } else if (diff < 24 * HOUR_MILLIS) {
            return diff / HOUR_MILLIS + " часов назад";
        } else if (diff < 48 * HOUR_MILLIS) {
            return "вчера";
        } else if (diff < 4 * DAY_MILLIS) {
            return diff / DAY_MILLIS + " дня назад";
        } else {
            Date date = new Date();
            date.setTime(date.getTime() - diff);
            DateFormat format = new SimpleDateFormat("MM.dd.yyyy hh:mm", Locale.ENGLISH);
            String longAgo =  format.format(date).toString();

            return longAgo;
        }


    }
}
