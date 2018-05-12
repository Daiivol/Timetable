package com.android.garvit.timetable;

import java.util.List;

public class Clash {
    String sub1;
    String sub2;
    String Day;
    String Period;

    public Clash(String sub1, String sub2, String day, String period) {
        this.sub1 = sub1;
        this.sub2 = sub2;
        Day = day;
        Period = period;
    }

    public String getSub1() {
        return sub1;
    }

    public String getSub2() {
        return sub2;
    }

    public String getDay() {
        return Day;
    }

    public String getPeriod() {
        return Period;
    }
}

