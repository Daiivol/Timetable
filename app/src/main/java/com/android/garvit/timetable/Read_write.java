package com.android.garvit.timetable;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class Read_write {

    private Context context;

    public Read_write(Context context) {
        this.context = context;
    }

    void write(Subject subject){
        String filename = "Timetable.txt";
        File file = new File(context.getFilesDir(), filename);
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(subject);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void empty_file() {
        String filename = "Timetable.txt";
        try {
            new FileOutputStream(filename).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void write_list(List<Subject> subjectList){
        String filename = "Timetable.txt";
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for(Subject S : subjectList){
            oos.writeObject(S);
            }
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Subject> read() {
        String filename = "Timetable.txt";
        List<Subject> SubjectList = null;
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois = new ObjectInputStream(fis);
            SubjectList = (List<Subject>) ois.readObject();
            ois.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return SubjectList;
    }
}
