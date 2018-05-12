package com.android.garvit.timetable;

import java.io.Serializable;

public class Period implements Serializable {
    private String Day;
    private String Period;

    public Period(String day, String period) {
        Day = day;
        Period = period;
    }

    public String getDay() {
        return Day;
    }

    public String getPeriod() {
        return Period;
    }
}
