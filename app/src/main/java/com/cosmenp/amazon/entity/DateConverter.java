package com.cosmenp.amazon.entity;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.text.DateFormat;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public class DateConverter {

    @TypeConverter
    public static Date toDate(String date) {
        Date parsedDate = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            parsedDate = formatter.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parsedDate;
    }

    @TypeConverter
    public static String toTimestamp(Date date) {
        return date == null ? null : new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

}
