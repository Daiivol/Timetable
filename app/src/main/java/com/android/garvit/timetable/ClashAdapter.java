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

public class ClashAdapter extends RecyclerView.Adapter<ClashAdapter.ClashViewHolder> implements View.OnClickListener {

    private Context mcontext;
    private List<Clash> ClashList;
    private DatabaseHelper databaseHelper;

    public ClashAdapter(Context mcontext, DatabaseHelper dh) {
        this.mcontext = mcontext;
        databaseHelper = dh;
        List<Clash> mid_clash = databaseHelper.get_all_clashes();
        ClashList = get_clashes(mid_clash);
    }

    @Override
    public void onClick(View v) {

    }

    class ClashViewHolder extends RecyclerView.ViewHolder {
        TextView Clash_text;

        public ClashViewHolder(View itemView) {
            super(itemView);
            Clash_text = itemView.findViewById(R.id.clash_text);

        }
    }

    @Override
    public ClashViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        View view = inflater.inflate(R.layout.view_clashes_cardview, null, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        ClashViewHolder holder = new ClashViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ClashViewHolder holder, final int position) {
        Clash clash = ClashList.get(position);
        StringBuilder info = new StringBuilder(clash.getSub1() + " Clashes with "
                + clash.getSub2() + " on period: \n" + clash.getDay() + " " + clash.getPeriod());
//        List<Period> periodlist = clash.getPeriodlist();
//        for(Period period: periodlist)
//         { StringBuilder info_period = new StringBuilder(period.getDay() + " " + period.getPeriod()+ "\n");
//          info.append(info_period);
//         }
        holder.Clash_text.setText(info);

    }

    @Override
    public int getItemCount() {
        return ClashList.size();
    }


    public List<Clash> get_clashes(List<Clash> clashes){
        List<Clash> clashList = new ArrayList<>();
        ArrayList<String> S = new ArrayList<>();
        for(Clash clashList1: clashes){
                if(clashList.isEmpty()){
                    clashList.add(clashList1);
                    String c1 = clashList1.getSub1() + clashList1.getSub2();
                    String c2 = clashList1.getSub2() + clashList1.getSub1();
                    S.add(c1);
                    S.add(c2);
                }
                else if (!clashList.isEmpty()){
                    String c1 = clashList1.getSub1() + clashList1.getSub2();
                    String c2 = clashList1.getSub2() + clashList1.getSub1();
                    if(S.contains(c1)&&!S.contains(c2)){
                                S.add(c2);
                            }
                            else if(S.contains(c2)&&!S.contains(c1)){
                        S.add(c1);
                    }
                    else if(!S.contains(c2)&&!S.contains(c1)){
                        S.add(c1);
                        S.add(c2);
                        clashList.add(clashList1);
                    }

//                        for(String C : S1){
//                            if(C.equals(c1)&&!C.equals(c2)){
//                                S.add(c2);
//                                break;
//                            }
//                            else if(C.equals(c2)&&!C.equals(c1)){
//                                S.add(c1);
//                                break;
//                            }
//                            else if(!C.equals(c1)&&!C.equals(c2)){
//                                S.add(c1);
//                                S.add(c2);
//                                clashList.add(clashList1);
//                            }
//
//                        }

                }


        }
        return clashList;

    }
}