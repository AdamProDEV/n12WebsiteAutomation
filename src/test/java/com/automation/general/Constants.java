package com.automation.general;

public class Constants {


    public enum barNavigation {

        SECURITY("ביטחוני"),
        POLITICAL("פוליטי"),
        CRIMINAL("פלילי"),
        INSIDE("פנים"),
        ECONOMY("כלכלה"),
        MAGAZINE("המגזין"),
        SPORT("ספורט"),
        PODCASTS("הפודקאסטים"),
        CULTURE("תרבות"),
        TIP12("TIP12"),
        PROGRAMS("תוכניות");


        public String value;
        barNavigation(String value){

            this.value = value;
        }

    }
}
