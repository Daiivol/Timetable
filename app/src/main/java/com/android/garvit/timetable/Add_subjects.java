package com.android.garvit.timetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Add_subjects extends AppCompatActivity {

    private android.support.v7.widget.Toolbar toolbar;
    private DatabaseHelper dh;
    private Subject subject;
    private ArrayList<String> list;  //convert each string to corresponding period using String[] splited = str.split("\\s+");
    private EditText subject_name, room, prof;
    private android.support.v7.widget.SwitchCompat add_to_table;
    private RecyclerView recyclerView;
    private AddAdapter addAdapter;
    private Toast toast;
    boolean add_in_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subjects);
        inittoolbar();
        dh = new DatabaseHelper(this);

        subject_name = findViewById(R.id.enter_subject_name);
        room = findViewById(R.id.enter_room);
        prof = findViewById(R.id.enter_prof);
        add_to_table = findViewById(R.id.add_to_table);

        //To show at least one row
        if(list == null || list.size() == 0) {
            list = new ArrayList<>();
            list.add("");
        }
        recyclerView = findViewById(R.id.recyclerperiod);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addAdapter = new AddAdapter(list,this);
        recyclerView.setAdapter(addAdapter);

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

            String subject = subject_name.getText().toString();
            String Room = room.getText().toString();
            String Prof = prof.getText().toString();

            List<Period> PeriodList = new ArrayList<>();
            list = addAdapter.getStepList();
            for(String S :list){
                Log.v("new period", S);
                if (!S.isEmpty()) {
                    String[] split = S.split("\\s+");
                    String day = split[0].toString();
                    String period = split[1].toString();
                    Period new_period = new Period(day, period);
                    PeriodList.add(new_period);
                }
            }
            add_in_table= add_to_table.isChecked();

            Subject subject1 = new Subject(subject,Room,Prof,PeriodList,add_in_table);
            if(subject.isEmpty()||Room.isEmpty()||PeriodList.size()==0){
                toast.makeText(Add_subjects.this, "Please fill all necessary fields",Toast.LENGTH_LONG).show();
            }
            else {
                dh.add_subject_to_table(subject1);
                toast.makeText(Add_subjects.this, "Subject "+ subject + " Added!",Toast.LENGTH_LONG).show();
                finish();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
