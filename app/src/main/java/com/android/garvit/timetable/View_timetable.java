package com.android.garvit.timetable;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class View_timetable extends AppCompatActivity {
//    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        context= this.context;
//        int resID = context.getResources().getIdentifier(String, "layout", context.getPackageName());
        String S = new String("activity_view_timetable");
        int resID = getResources().getIdentifier(S, "layout", getPackageName());
        setContentView(resID);
    }
}
