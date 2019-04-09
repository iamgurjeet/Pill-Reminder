package com.o7services.pillreminder.fragments;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.o7services.pillreminder.R;
import com.o7services.pillreminder.dataModels.History;
import com.o7services.pillreminder.dataModels.PillBox;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        TableLayout stk = view.findViewById(R.id.table_history);

        TableRow tbrow0 = new TableRow(container.getContext());

        TextView tt1 = new TextView(container.getContext());
        tt1.setText(getString(R.string.pill_name_his));
        tt1.setTextColor(Color.WHITE);
        tt1.setGravity(Gravity.CENTER);
        tt1.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt1);

        TextView tt2 = new TextView(container.getContext());
        tt2.setText(getString(R.string.pill_date_taken_his));
        tt2.setTextColor(Color.WHITE);
        tt2.setGravity(Gravity.CENTER);
        tt2.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt2);

        TextView tt3 = new TextView(container.getContext());
        tt3.setText(getString(R.string.pill_time_taken_his));
        tt3.setTextColor(Color.WHITE);
        tt3.setGravity(Gravity.CENTER);
        tt3.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tt3);

        stk.addView(tbrow0);

        PillBox pillBox = new PillBox();

        for (History history: pillBox.getHistory(container.getContext())){
            TableRow tbrow = new TableRow(container.getContext());

            TextView t1v = new TextView(container.getContext());
            t1v.setText(history.getPillName());
            t1v.setTextColor(Color.WHITE);
            t1v.setGravity(Gravity.CENTER);
            t1v.setMaxEms(4);
            tbrow.addView(t1v);

            TextView t2v = new TextView(container.getContext());
            String date = history.getDateString();
            t2v.setText(date);
            t2v.setTextColor(Color.WHITE);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);

            TextView t3v = new TextView(container.getContext());

            int nonMilitaryHour = history.getHourTaken() % 12;
            if (nonMilitaryHour == 0)
                nonMilitaryHour = 12;

            String minute;
            if (history.getMinuteTaken() < 10)
                minute = "0" + history.getMinuteTaken();
            else
                minute = "" + history.getMinuteTaken();

            String time = nonMilitaryHour + ":" + minute + " " + history.getAm_pmTaken();
            t3v.setText(time);
            t3v.setTextColor(Color.WHITE);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);

            stk.addView(tbrow);
        }
        return view;
    }

}
