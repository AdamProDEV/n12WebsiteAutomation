package com.automation.dev;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTime {

    @Test
    void dateTime(){

        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd.MM.yy EEEE", new Locale("he"));
        System.out.println(formatter.format(new Date()));
    }
}
