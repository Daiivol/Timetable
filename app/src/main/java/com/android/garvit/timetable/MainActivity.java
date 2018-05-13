package com.android.garvit.timetable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardView subject_cv;
    private CardView clash_cv,timetable_cv,add_cv;
    private DatabaseHelper dh;
    private Toast toast;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupuiviews();

        dh = new DatabaseHelper(this);

        prefs = getSharedPreferences("com.android.garvit.timetable", MODE_PRIVATE);

        add_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Add_subjects.class);
                startActivity(intent);
            }
        });

        subject_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_subjects.class);
                startActivity(intent);
            }
        });

        clash_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, View_clashes.class);
                startActivity(intent);
            }
        });
        timetable_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Clash> clashList = dh.get_all_clashes();
                if(!clashList.isEmpty()){
                    toast.makeText(MainActivity.this, "Please remove all course clashes to check your timetable. Tap on Check Clashes and resolve them",Toast.LENGTH_LONG).show();
                }
                else if(clashList.isEmpty()) {
                    Intent intent = new Intent(MainActivity.this, View_timetable.class);
                    startActivity(intent);
                }
            }
        });

    }

    private void setupuiviews(){
        subject_cv = (CardView)findViewById(R.id.subject_cv);
        clash_cv = (CardView)findViewById(R.id.clash_cv);
        timetable_cv = (CardView)findViewById(R.id.timetable_cv);
        add_cv = (CardView)findViewById(R.id.add_cv);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            List<Period> PeriodList = new ArrayList<>();
            PeriodList.add(new Period("Sun","1"));
            PeriodList.add(new Period("Mon","2"));
            PeriodList.add(new Period("Tue","3"));
            PeriodList.add(new Period("Wed","4"));
            PeriodList.add(new Period("Thu","5"));
            dh.add_subject_to_table(new Subject("Example Subject", "101","Garvit Bansal",PeriodList,true));
            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }



}
