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
    private String table = "Subjects";
    private String id = "Id",name = "Name", room = "Room", prof = "Prof", day = "Day", period = "Period";
    private String add_boolean = "InTable";

    public DatabaseHelper(Context context){
        super(context, database_name,null,  database_version);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String Create_table = "create table " + table + " ( " + id + " integer primary key autoincrement , " + name + " text , " + room + " text , " + prof + " text , " + day + " text , " + period + " text , " + add_boolean + " integer )";
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
        Cursor cursor = db.query(table, new String[] {id,name,room,prof,day,period,add_boolean},name + "= ?",new String[] { new_name} ,null,null,null );

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
        Subject subject = new Subject(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),PeriodList,new_b);
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

}
