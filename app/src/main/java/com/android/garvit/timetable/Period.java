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

    public void setDay(String day) {
        Day = day;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }
}
