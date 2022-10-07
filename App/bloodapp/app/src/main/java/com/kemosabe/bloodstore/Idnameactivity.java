package com.kemosabe.bloodstore;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Idnameactivity extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Name;
    private final String[] Bloodgrp;
    private final String[] Phno;
    private final String[] Cat;

    public Idnameactivity(Activity context, String[] stuname, String[] stbg, String[] stphno, String[] stcat) {
        super(context, R.layout.activity_idnameactivity, stuname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.Name = stuname;
        this.Bloodgrp = stbg;
        this.Phno = stphno;
        this.Cat = stcat;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_idnameactivity, null, true);

        TextView name = (TextView) rowView.findViewById(R.id.stu_name);
        TextView bloodgrp = (TextView) rowView.findViewById(R.id.stu_Course);
        TextView phno = (TextView) rowView.findViewById(R.id.stu_Batch);
        TextView cat = (TextView) rowView.findViewById(R.id.stu_Gender);

        name.setText(Name[position].trim());
        bloodgrp.setText(Bloodgrp[position].trim());
        phno.setText(Phno[position].trim());
        cat.setText(Cat[position].trim());

        return rowView;

    }

    ;
}
