package com.example.flickrimagesseeker.utils;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

public class Converters {

    public static String longToStringDate (long longDate) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(longDate * 1000);
        return DateFormat.format("dd/MM/yyyy", cal).toString();
    }
}
