package com.android.garvit.timetable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "Subject.db";
    private static final int database_version = 1;
    //all columns for the table
    private static final String table = "Subjects";
    private static final String id = "Id",name = "Name", room = "Room", prof = "Prof", day = "Day", period = "Period";
    private static final String add_boolean = "InTable";

    public DatabaseHelper(Context context){
        super(context, database_name,null,  database_version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_table = "create table " + table + " ( " + id + " integer primary key autoincrement , " + name + " text , " + room + " text , "
                + prof + " text , " + day + " text , " + period + " text , " + add_boolean + " integer )";
        db.execSQL(Create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + table);

        onCreate(db);
    }

    public void add_subject_to_table(Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Period> PeriodList = subject.getPeriodList();
        for(Period period1 : PeriodList) {
            ContentValues values = new ContentValues();
            values.put(name, subject.getName());
            values.put(room, subject.getRoom());
            values.put(prof, subject.getProf());
            values.put(day, period1.getDay());
            values.put(period, period1.getPeriod());
            values.put(add_boolean, subject.isAdd_table());
            subject.Log();
            db.insert(table,null,values);

        }

        db.close();
    }

    public Subject get_subject_from_table(String new_name){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table, new String[] {id,name,room,prof,day,period,add_boolean},name + "= ?",
                new String[] { new_name} ,null,null,null );

        List<Period> PeriodList = new ArrayList<>();

        //ADDING ALL PERIODS FROM ALL ROWS TO PERIODLIST
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            do {
                Period period = new Period(cursor.getString(4),cursor.getString(5));
                PeriodList.add(period);
            }while (cursor.moveToNext());
        }

        // ADDING ONLY FIRST ROW FROM ROWS WITH MULTIPLE PERIODS
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        int i;
        i = Integer.parseInt(cursor.getString(6));
        boolean new_b = (i!=0);
        Subject subject = new Subject(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),cursor.getString(2),cursor.getString(3),PeriodList,new_b);
        subject.Log();
        db.close();
        return subject;
    }

    public ArrayList<String> get_all_names_of_subjects(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> Names = new ArrayList<String>();
        Cursor cursor = db.query(table, new String[] {name},null,null ,name,null,name );

        if(cursor!=null) {
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            do {
                String Name = cursor.getString(0);
                Names.add(Name);
            }while (cursor.moveToNext());
        }

        db.close();
        return Names;
    }

    public List<Subject> get_all_subjects(){
        //get all distinct subject names
        ArrayList<String> Names = get_all_names_of_subjects();
        //make list of subjects
        List<Subject> SubjectList = new ArrayList<>();
        for (String name :Names){
            //taking each subject individually from database
            Subject subject = get_subject_from_table(name);
            //adding to list
            SubjectList.add(subject);
        }
        return  SubjectList;
    }

    public void delete_subject(Subject subject){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table, name+ "=?",new String[]{subject.getName()});
        db.close();
    }

    public void update_subject(Subject subject_old,Subject subject_new) {
        SQLiteDatabase db = this.getWritableDatabase();
        // putting old period values to null and changing other values normally
        List<Period> PeriodList_old = subject_old.getPeriodList();
        List<Period> PeriodList_new = subject_new.getPeriodList();
        if (PeriodList_new.size() < PeriodList_old.size()){
            for (Period period1 : PeriodList_old) {
                ContentValues values = new ContentValues();
                values.put(name, subject_new.getName());
                values.put(room, subject_new.getRoom());
                values.put(prof, subject_new.getProf());
                values.putNull(day);
                values.putNull(period);
                values.put(add_boolean, subject_new.isAdd_table());
                subject_new.Log();
                db.update(table, values, name + " = ? and " + day + " = ? and " + period + " = ?",
                        new String[]{subject_old.getName(), period1.getDay(), period1.getPeriod()});
                //putting new values in place of null
                if(PeriodList_new.size()!=0) {
                    Period period2 = PeriodList_new.get(0);
                    PeriodList_new.remove(0);

                    ContentValues values_new = new ContentValues();
                    values_new.put(day, period1.getDay());
                    values_new.put(period, period1.getPeriod());
                    subject_new.Log();
                    db.update(table, values_new, name + " = ? and " + day + " is null and " + period + " is null",
                            new String[]{subject_new.getName()});
                }
            }
            //deleting values which still have null
            db.delete(table, name + " = ? and " + day + " is null and " + period + " is null",
                    new String[]{subject_new.getName()});
        }

        else if(PeriodList_new.size()==PeriodList_old.size()){
            for (Period period1 : PeriodList_old) {
                Period period2 = PeriodList_new.get(0);
                PeriodList_new.remove(0);
                ContentValues values = new ContentValues();
                values.put(name, subject_new.getName());
                values.put(room, subject_new.getRoom());
                values.put(prof, subject_new.getProf());
                values.put(day, period2.getDay());
                values.put(period, period2.getPeriod());
                values.put(add_boolean, subject_new.isAdd_table());
                subject_new.Log();
                //directly changing values
                db.update(table, values, name + " = ? and " + day + " = ? and " + period + " = ?",
                        new String[]{subject_old.getName(), period1.getDay(), period1.getPeriod()});
            }

        }
        else if(PeriodList_new.size() > PeriodList_old.size()){
            for (Period period2 : PeriodList_new) {
                ContentValues values = new ContentValues();
                values.put(name, subject_new.getName());
                values.put(room, subject_new.getRoom());
                values.put(prof, subject_new.getProf());
                values.put(day, period2.getDay());
                values.put(period, period2.getPeriod());
                values.put(add_boolean, subject_new.isAdd_table());
                subject_new.Log();
                if(PeriodList_old.size()!=0) {
                    Period period1 = PeriodList_old.get(0);
                    PeriodList_old.remove(0);
                    //changing old values when available
                    db.update(table, values, name + " = ? and " + day + " = ? and " + period + " = ?",
                            new String[]{subject_old.getName(), period1.getDay(), period1.getPeriod()});
                }
                else if (PeriodList_old.size()==0){
                    //adding new values when old is changed completely and need more
                    db.insert(table,null, values);
                }
            }
        }
    }

    public List<Timetable> get_all_periods(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table, new String[] {id,name,room,prof,day,period,add_boolean},null,
                null ,null,null,null );

        List<Timetable> TimetableList = new ArrayList<>();
        if(cursor!=null) {
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            do {int i;
                i = Integer.parseInt(cursor.getString(6));
                boolean new_b = (i!=0);
                Timetable timetable = new Timetable(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5),new_b);
                TimetableList.add(timetable);
            }while (cursor.moveToNext());
        }

        db.close();
        return  TimetableList;
    }

    public List<Clash> get_all_clashes(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Clash> clashList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select t1.Name, t2.Name , t1.Day , t1.period " +
                "from Subjects t1, Subjects t2 where t1.day= t2.day " +
                "and t1.period = t2.period and t1.name != t2.name order by t1.day;",null);

        if(cursor!=null) {
            cursor.moveToFirst();
        }
        if(cursor.moveToFirst()){
            do {
                String Sub1 = cursor.getString(0);
                String Sub2 = cursor.getString(1);
                String day = cursor.getString(2);
                String period = cursor.getString(3);
                Clash clash = new Clash(Sub1,Sub2,day,period);
                clashList.add(clash);
            }while (cursor.moveToNext());
        }

        db.close();
        return clashList;
    }
}
