package com.android.garvit.timetable;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class View_timetable extends AppCompatActivity {
    //    private Context context;
    TextView sun_1, sun_2, sun_3, sun_4, sun_5, sun_6, sun_7, sun_8, sun_9;
    TextView mon_1, mon_2, mon_3, mon_4, mon_5, mon_6, mon_7, mon_8, mon_9;
    TextView tue_1, tue_2, tue_3, tue_4, tue_5, tue_6, tue_7, tue_8, tue_9;
    TextView wed_1, wed_2, wed_3, wed_4, wed_5, wed_6, wed_7, wed_8, wed_9;
    TextView thu_1, thu_2, thu_3, thu_4, thu_5, thu_6, thu_7, thu_8, thu_9;
    DatabaseHelper db;
    List<Timetable> timetables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        context= this.context;
//        int resID = context.getResources().getIdentifier(String, "layout", context.getPackageName());
        String S = new String("activity_view_timetable");
        int resID = getResources().getIdentifier(S, "layout", getPackageName());
        setContentView(resID);
        textinitializer();

        db = new DatabaseHelper(this);



        timetables = db.get_all_periods();

        for (Timetable timetable : timetables) {
            String Name = timetable.getName();
            String Room = timetable.getRoom();
            String Prof = timetable.getProf();
            String Day = timetable.getDay();
            String Period = timetable.getPeriod();
            String period = Day + "_" + Period;
            String info = Name + "\n" + Room ;
            Log.v("timetable",period +"\n" + info);
            boolean Add_table = timetable.isAdd_table();
            if (Add_table) {
                if(period.equals("Sun_1")){
                    sun_1.setText(info);
                }
                else if (period.equals("Sun_2")){
                    sun_2.setText(info);
                }
                else if (period.equals("Sun_3")){
                    sun_3.setText(info);
                }
                else if (period.equals("Sun_4")){
                    sun_4.setText(info);
                }
                else if (period.equals("Sun_5")){
                    sun_5.setText(info);
                }
                else if (period.equals("Sun_6")){
                    sun_6.setText(info);
                }
                else if (period.equals("Sun_7")){
                    sun_7.setText(info);
                }
                else if (period.equals("Sun_8")){
                    sun_8.setText(info);
                }
                else if (period.equals("Sun_9")){
                    sun_9.setText(info);
                }
                else if (period.equals("Mon_1")) {
                    mon_1.setText(info);
                }
                else if (period.equals("Mon_2")){
                    mon_2.setText(info);
                }
                else if (period.equals("Mon_3")){
                    mon_3.setText(info);
                }
                else if (period.equals("Mon_4")){
                    mon_4.setText(info);
                }
                else if (period.equals("Mon_5")){
                    mon_5.setText(info);
                }
                else if (period.equals("Mon_6")){
                    mon_6.setText(info);
                }
                else if (period.equals("Mon_7")){
                    mon_7.setText(info);
                }
                else if (period.equals("Mon_8")){
                    mon_8.setText(info);
                }
                else if (period.equals("Mon_9")){
                    mon_9.setText(info);
                }
                else if (period.equals("Tue_1")) {
                    tue_1.setText(info);
                }
                else if (period.equals("Tue_2")){
                    tue_2.setText(info);
                }
                else if (period.equals("Tue_3")){
                    tue_3.setText(info);
                }
                else if (period.equals("Tue_4")){
                    tue_4.setText(info);
                }
                else if (period.equals("Tue_5")){
                    tue_5.setText(info);
                }
                else if (period.equals("Tue_6")){
                    tue_6.setText(info);
                }
                else if (period.equals("Tue_7")){
                    tue_7.setText(info);
                }
                else if (period.equals("Tue_8")){
                    tue_8.setText(info);
                }
                else if (period.equals("Tue_9")){
                    tue_9.setText(info);
                }
                else if (period.equals("Wed_1")) {
                    wed_1.setText(info);
                }
                else if (period.equals("Wed_2")){
                    wed_2.setText(info);
                }
                else if (period.equals("Wed_3")){
                    wed_3.setText(info);
                }
                else if (period.equals("Wed_4")){
                    wed_4.setText(info);
                }
                else if (period.equals("Wed_5")){
                    wed_5.setText(info);
                }
                else if (period.equals("Wed_6")){
                    wed_6.setText(info);
                }
                else if (period.equals("Wed_7")){
                    wed_7.setText(info);
                }
                else if (period.equals("Wed_8")){
                    wed_8.setText(info);
                }
                else if (period.equals("Wed_9")){
                    wed_9.setText(info);
                }
                else if (period.equals("Thu_1")) {
                    thu_1.setText(info);
                }
                else if (period.equals("Thu_2")){
                    thu_2.setText(info);
                }
                else if (period.equals("Thu_3")){
                    thu_3.setText(info);
                }
                else if (period.equals("Thu_4")){
                    thu_4.setText(info);
                }
                else if (period.equals("Thu_5")){
                    thu_5.setText(info);
                }
                else if (period.equals("Thu_6")){
                    thu_6.setText(info);
                }
                else if (period.equals("Thu_7")){
                    thu_7.setText(info);
                }
                else if (period.equals("Thu_8")){
                    thu_8.setText(info);
                }
                else if (period.equals("Thu_9")){
                    thu_9.setText(info);
                }

            }


        }
    }

        private void textinitializer(){
            sun_1 = findViewById(R.id.sun_1);
            sun_2 = findViewById(R.id.sun_2);
            sun_3 = findViewById(R.id.sun_3);
            sun_4 = findViewById(R.id.sun_4);
            sun_5 = findViewById(R.id.sun_5);
            sun_6 = findViewById(R.id.sun_6);
            sun_7 = findViewById(R.id.sun_7);
            sun_8 = findViewById(R.id.sun_8);
            sun_9 = findViewById(R.id.sun_9);
            mon_1 = findViewById(R.id.mon_1);
            mon_2 = findViewById(R.id.mon_2);
            mon_3 = findViewById(R.id.mon_3);
            mon_4 = findViewById(R.id.mon_4);
            mon_5 = findViewById(R.id.mon_5);
            mon_6 = findViewById(R.id.mon_6);
            mon_7 = findViewById(R.id.mon_7);
            mon_8 = findViewById(R.id.mon_8);
            mon_9 = findViewById(R.id.mon_9);
            tue_1 = findViewById(R.id.tue_1);
            tue_2 = findViewById(R.id.tue_2);
            tue_3 = findViewById(R.id.tue_3);
            tue_4 = findViewById(R.id.tue_4);
            tue_5 = findViewById(R.id.tue_5);
            tue_6 = findViewById(R.id.tue_6);
            tue_7 = findViewById(R.id.tue_7);
            tue_8 = findViewById(R.id.tue_8);
            tue_9 = findViewById(R.id.tue_9);
            wed_1 = findViewById(R.id.wed_1);
            wed_2 = findViewById(R.id.wed_2);
            wed_3 = findViewById(R.id.wed_3);
            wed_4 = findViewById(R.id.wed_4);
            wed_5 = findViewById(R.id.wed_5);
            wed_6 = findViewById(R.id.wed_6);
            wed_7 = findViewById(R.id.wed_7);
            wed_8 = findViewById(R.id.wed_8);
            wed_9 = findViewById(R.id.wed_9);
            thu_1 = findViewById(R.id.thu_1);
            thu_2 = findViewById(R.id.thu_2);
            thu_3 = findViewById(R.id.thu_3);
            thu_4 = findViewById(R.id.thu_4);
            thu_5 = findViewById(R.id.thu_5);
            thu_6 = findViewById(R.id.thu_6);
            thu_7 = findViewById(R.id.thu_7);
            thu_8 = findViewById(R.id.thu_8);
            thu_9 = findViewById(R.id.thu_9);
        }
}
