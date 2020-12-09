package com.pavel.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Utils {
    private static final String DATE_PATTERN = "MM/dd/yyyy";
    private static final DateFormat df = new SimpleDateFormat(DATE_PATTERN);
    static Random random = new Random();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz) {
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static Date getToday() {
        return Calendar.getInstance().getTime();
    }

    public static int randInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static <T> T getRandom(T[] array) {
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }

    public static String format(Date date) {
        return df.format(date);
    }

    public static Date parse(String string) throws ParseException {
        return df.parse(string);
    }
}
