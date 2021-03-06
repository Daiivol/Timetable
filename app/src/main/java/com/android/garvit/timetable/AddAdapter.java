package com.android.garvit.timetable;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.ViewHolder> {

    Context context;
    ArrayList<String> steps;
    String hint;
    public AddAdapter(ArrayList<String> steps, Context context){
        this.steps = steps;
        this.hint = "Eg- Mon 1, Thu 3, Sun 7, Wed 2";
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageButton plus, minus;
        EditText step;

        public ViewHolder(View itemView) {
            super(itemView);
            plus = (ImageButton) itemView.findViewById(R.id.plus);
            minus = (ImageButton) itemView.findViewById(R.id.minus);
            step = (EditText) itemView.findViewById(R.id.step);

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    try {
                        steps.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, steps.size());
                    }catch (ArrayIndexOutOfBoundsException e){e.printStackTrace();}
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    try {
                        steps.add(position + 1, "");
                        notifyItemInserted(position + 1);
                        notifyItemRangeChanged(position, steps.size());

                    }catch (ArrayIndexOutOfBoundsException e){e.printStackTrace();}
                }
            });

            step.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //step.setSelection(0);  //step.setSelection(step.getText().length()); // End point
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    steps.set(getAdapterPosition(), s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    @Override
    public AddAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.content_add_subjects, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AddAdapter.ViewHolder holder, final int i) {
        int x = holder.getLayoutPosition();

        if(steps.get(x).length() > 0) {
            holder.step.setText(steps.get(x));
            holder.step.setSelection(0);
        }
        else{
            holder.step.setText(null);
            holder.step.setHint(hint);
            holder.step.requestFocus();
        }
    }

    public ArrayList<String> getStepList(){
        return steps;
    }

}
