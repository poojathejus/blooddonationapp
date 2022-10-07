package com.kemosabe.bloodstore;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyListView extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] Name;
    private final String[] Course;
    private final String[] Batch;
    private final String[] Gender;

    public MyListView(Activity context, String[] stuname, String[] stucourse, String[] stubatch, String[] stugender) {
        super(context, R.layout.customlist_student, stuname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.Name = stuname;
        this.Course = stucourse;
        this.Batch = stubatch;
        this.Gender = stugender;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.customlist_student, null, true);

        TextView name = (TextView) rowView.findViewById(R.id.stu_name);
        TextView course = (TextView) rowView.findViewById(R.id.stu_Course);
        TextView batch = (TextView) rowView.findViewById(R.id.stu_Batch);
        TextView gender = (TextView) rowView.findViewById(R.id.stu_Gender);

        name.setText(Name[position].trim());
        course.setText(Course[position].trim());
        batch.setText(Batch[position].trim());
        gender.setText(Gender[position].trim());

        return rowView;

    }

    ;
}
