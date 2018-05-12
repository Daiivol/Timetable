package com.android.garvit.timetable;

public class Timetable {
    private int Id;
    private String Name;
    private String Room;
    private String Prof;
    private String Day;
    private String Period;
    private boolean Add_table;

    public Timetable(int id, String name, String room, String prof, String day, String period, boolean add_table) {
        Id = id;
        Name = name;
        Room = room;
        Prof = prof;
        Day = day;
        Period = period;
        Add_table = add_table;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getRoom() {
        return Room;
    }

    public String getProf() {
        return Prof;
    }

    public String getDay() {
        return Day;
    }

    public String getPeriod() {
        return Period;
    }

    public boolean isAdd_table() {
        return Add_table;
    }
}
