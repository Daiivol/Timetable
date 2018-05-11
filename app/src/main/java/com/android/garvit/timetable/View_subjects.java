package com.android.garvit.timetable;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class View_subjects extends AppCompatActivity {

//    private android.support.v7.widget.Toolbar toolbar;
    private FloatingActionButton add;
    private RecyclerView recyclerView;
    private Context context;
    private SQLiteDatabase mdatabase;
    List<Subject> SubjectList;
    List<Period> PeriodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_subjects);
        //setting back to home button if theme is noactionbar
        inittoolbar();

        add =  findViewById(R.id.sub_fab);
        recyclerView =  findViewById(R.id.subject_cycle);
        SubjectList = new ArrayList<>();
        PeriodList = new ArrayList<>();


        //basic settings to run recycle view
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // adding data to SubjectList
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(View_subjects.this, Add_subjects.class);
                startActivity(intent);
            }
        });
        // sample add for now
        DatabaseHelper dh = new DatabaseHelper(this);

        PeriodList.add(new Period("Mon","1"));
        PeriodList.add(new Period("Sun","3"));
        dh.add_subject_to_table(new Subject(1,"Math","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(2,"Msh","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(3,"Mswath","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(4,"Mawdwwdth","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(5,"Ma2eth","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(6,"Mdath","105","Raj", PeriodList,true));
        dh.add_subject_to_table(new Subject(7,"Mawwdth","105","Raj", PeriodList,true));

//        mdatabase = dh.getWritableDatabase();
//        // using adapter to put values in cardview
//        SubjectList = dh.get_all_subjects();
        SubjectAdapter adapter = new SubjectAdapter(this,dh);
        //putting the cardview holder in recycle view
        recyclerView.setAdapter(adapter);
//        adapter.swapCursor(get_all_subjects());

    }

    private void inittoolbar(){
//        toolbar = (Toolbar) findViewById(R.id.sub_toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    private Cursor get_all_subjects(DatabaseHelper dh){
        mdatabase = dh.getWritableDatabase();
        return mdatabase.rawQuery("select * from Subjects",null);
    }


}
