package com.android.garvit.timetable;

import android.util.Log;

import java.io.Serializable;
import java.util.List;

public class Subject implements Serializable{
    private int Id;
    private String Name;
    private String Room;
    private String Prof;
    private List<Period> PeriodList;
    private boolean Add_table;

    public Subject(int id, String name, String room, String prof, List<Period> periodList, boolean add_table) {
        Id = id;
        Name = name;
        Room = room;
        Prof = prof;
        PeriodList = periodList;
        Add_table = add_table;
    }

    public void Log(){
        String id1 ;
        id1 =String.valueOf(Id);
        String bool = String.valueOf(Add_table);
        String mssg = id1 + Name + Room + Prof + bool + "\n";
        Log.v("subject",mssg);

        for ( Period period :PeriodList){
            int i=1;
            String msg = period.getDay() + period.getPeriod() + "\n";
            Log.v("period "+ String.valueOf(i),msg);
        }
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public List<Period> getPeriodList() {
        return PeriodList;
    }

    public void setPeriodList(List<Period> periodList) {
        PeriodList = periodList;
    }

    public boolean isAdd_table() {
        return Add_table;
    }

    public void setAdd_table(boolean add_table) {
        Add_table = add_table;
    }
}
