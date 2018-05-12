package com.android.garvit.timetable;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> implements View.OnClickListener{

    private Context mcontext;
    private List<Subject> SubjectList;
    private DatabaseHelper databaseHelper;

    public SubjectAdapter(Context mcontext, DatabaseHelper dh) {
        this.mcontext = mcontext;
        databaseHelper = dh;
        SubjectList = databaseHelper.get_all_subjects();
    }

    @Override
    public void onClick(View v) {

    }

    class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView Subject_text, Subject_info;
        Button delete_b,edit_b;

        public SubjectViewHolder(View itemView) {
            super(itemView);
            Subject_text = itemView.findViewById(R.id.subject_text);
            Subject_info = itemView.findViewById(R.id.subject_info_text);
            delete_b = itemView.findViewById(R.id.delete_b);
            edit_b = itemView.findViewById(R.id.edit_b);


        }
    }

    @Override
    public SubjectViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.view_subject_cardview, null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        SubjectViewHolder holder = new SubjectViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final SubjectViewHolder holder, final int position) {
        final Subject subject = SubjectList.get(position);
        String info = "Room:"+ subject.getRoom() +" Prof:" + subject.getProf();
        holder.Subject_text.setText(subject.getName());
        holder.Subject_info.setText(info);
        holder.edit_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject old_sub = SubjectList.get(position);
                //next four lines use new values (get them from add_layout again)
                List<Period> PeriodList = new ArrayList<>();
                PeriodList.add(new Period("Wed","2"));
                PeriodList.add(new Period("Thu","4"));
                PeriodList.add(new Period("Mon","3"));
                Subject new_sub = new Subject(1,"Science","202","Babu", PeriodList,false);
                //updating database
                databaseHelper.update_subject(old_sub,new_sub);
                SubjectList.set(position,new_sub);
                notifyItemChanged(holder.getAdapterPosition());

            }
        });
        holder.delete_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Subject old_sub = SubjectList.get(position);
                SubjectList.remove(position);
                databaseHelper.delete_subject(old_sub);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, SubjectList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return SubjectList.size();
    }


}
