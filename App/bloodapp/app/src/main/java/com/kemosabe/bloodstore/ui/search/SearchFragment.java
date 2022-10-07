package com.kemosabe.bloodstore.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kemosabe.bloodstore.MyListView;
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Utility;

import java.util.HashMap;
import java.util.Map;

public class SearchFragment extends Fragment {

    String[] Courselist = {"Select Course", "MCA", "BCA", "BSc.Computer Science", "BSc.Mathematics", "BSc.Visual Media"};
    String[] Batchlist = {"Batch", "2016-2019", "2017-2020", "2016-2021", "2019-2022"};
    String[] Bldgrplist = {"---Blood Group---", "A +ve", "B +ve", " AB +ve", " O +ve", " A -ve", " B -ve", " AB -ve", " O -ve"};

    Vibrator v;
    ListView stud_list;
    TextView tit;
    Spinner coursespin, batchspin, bloodgrpspin;
    Button searchbtn;

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);

        v = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        //        stud_list = root.findViewById(R.id.list_students);
        coursespin = root.findViewById(R.id.Vspin_course);
        batchspin = root.findViewById(R.id.Vspin_batch);
        bloodgrpspin = root.findViewById(R.id.search_spin_bldgrp);

        searchbtn = root.findViewById(R.id.searchbtn);
        stud_list = root.findViewById(R.id.list_students);

        tit = root.findViewById(R.id.titleshake);

        ArrayAdapter courseadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Courselist);

        coursespin.setAdapter(courseadapter);

        ArrayAdapter batchadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Batchlist);

        batchspin.setAdapter(batchadapter);

        ArrayAdapter bldgrpadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Bldgrplist);

        bloodgrpspin.setAdapter(bldgrpadapter);


        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sblood;

                sblood = bloodgrpspin.getSelectedItem().toString().trim();

                if (sblood.equals("---Blood Group---")) {

                    Toast.makeText(getContext(), "Choose a Blood Group", Toast.LENGTH_SHORT).show();
                    v.vibrate(200);
                    tit.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.shake));
                    stud_list.setAdapter(null);

                } else {

                    Volley_search_students(sblood);
                }
            }
        });

        return root;
    }

    public void Volley_search_students(final String bloodname) {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);


                if (!response.trim().equals("failed")) {


                    String respoarray[] = response.split("#");

                    String[] stname, stcourse, stbatch, stgender;

//                if (respoarray[0].trim().equals("success")) {
//
//
//                } else {
//                    Toast.makeText(getContext(), "Failed to Fetch", Toast.LENGTH_LONG).show();
//
//                }

//                ArrayAdapter listadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, respoarray);
//
//                stud_list.setAdapter(listadapter);


                    stname = respoarray[0].split(":");
                    stcourse = respoarray[1].split(":");
                    stbatch = respoarray[2].split(":");
                    stgender = respoarray[3].split(":");


                    MyListView myListView = new MyListView(getActivity(), stname, stcourse, stbatch, stgender);
                    stud_list.setAdapter(myListView);

                } else {
                    stud_list.setAdapter(null);

                    Toast.makeText(getContext(), "No Students Found", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
                Log.i("My Error", "" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> map = new HashMap<String, String>();
//                SharedPreferences sp=getSharedPreferences("booking_info", Context.MODE_PRIVATE);
                map.put("key", "student_search");
                map.put("bloodgroup", bloodname.trim());

                return map;
            }
        };
        queue.add(request);
    }
}