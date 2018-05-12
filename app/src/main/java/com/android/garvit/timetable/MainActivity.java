package com.android.garvit.timetable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private CardView subject_cv;
    private CardView clash_cv,timetable_cv;
    private DatabaseHelper dh;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupuiviews();

        dh = new DatabaseHelper(this);

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
    }



}
