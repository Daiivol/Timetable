package com.android.garvit.timetable;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class Add_subjects extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    private DatabaseHelper dh;
    private Subject subject;
    private List<Period> PeriodList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        inittoolbar();
        dh = new DatabaseHelper(this);
        PeriodList = new ArrayList<>();
        PeriodList.add(new Period("Mon","1"));
        PeriodList.add(new Period("Sun","3"));
        dh.add_subject_to_table(new Subject(1,"Math","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(2,"Msh","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(3,"Mswath","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(4,"Mawdwwdth","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(5,"Ma2eth","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(6,"Mdath","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(7,"Mawwdth","105","Raj", PeriodList,true));


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    void inittoolbar(){
        toolbar = findViewById(R.id.add_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        android.view.MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item){
        if(item.getItemId()== R.id.save_subject){
            // HERE ADD METHOD TO SAVE VALUES FROM EDIT TEXTS
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
