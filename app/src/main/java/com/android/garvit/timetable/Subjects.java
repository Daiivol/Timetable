package com.android.garvit.timetable;

import java.io.Serializable;
import java.util.List;

public class Subjects implements Serializable{
    private String Name;
    private String Room;
    private String Prof;
    private List<Periods> PeriodList;
    private boolean Add_table;

    public Subjects(String name, String room, String prof, List<Periods> periodList, boolean add_table) {
        Name = name;
        Room = room;
        Prof = prof;
        PeriodList = periodList;
        Add_table = add_table;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getProf() {
        return Prof;
    }

    public void setProf(String prof) {
        Prof = prof;
    }

    public List<Periods> getPeriodList() {
        return PeriodList;
    }

    public void setPeriodList(List<Periods> periodList) {
        PeriodList = periodList;
    }

    public boolean isAdd_table() {
        return Add_table;
    }

    public void setAdd_table(boolean add_table) {
        Add_table = add_table;
    }
}
