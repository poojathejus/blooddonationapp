package com.kemosabe.bloodstore.ui.nonfacdonorreg;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNonFacDonorReg extends Fragment {

    ListView showstafflist;
    String[] namearray, DOBarray, contactarray, bloodarray, idarray, addressarray;

    public FragmentNonFacDonorReg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_non_fac_donor_reg, container, false);


        showstafflist = root.findViewById(R.id.nonfaclist);


        Volley_getNonFacList();

        return root;
    }

    public void Volley_getNonFacList() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);


                if (!response.trim().equals("failed")) {


                    String respoarray[] = response.split("#");


                    idarray = respoarray[0].split(":");
                    namearray = respoarray[1].split(":");
                    DOBarray = respoarray[2].split(":");
                    addressarray = respoarray[3].split(":");
                    contactarray = respoarray[4].split(":");
                    bloodarray = respoarray[5].split(":");


                    MyListView myListView = new MyListView(getActivity(), namearray, DOBarray, contactarray, bloodarray);
                    showstafflist.setAdapter(myListView);

                    showstafflist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                            String sel_id = idarray[i];

                            Volley_RegDonorNonFac(sel_id, i);

                        }
                    });

                } else {
//                    stud_list.setAdapter(null);

                    Toast.makeText(getContext(), "No Faculty Found", Toast.LENGTH_SHORT).show();
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
                map.put("key", "getnonfaclist");

                return map;
            }
        };
        queue.add(request);
    }


    public void Volley_RegDonorNonFac(final String reg_id, final int ipos) {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);


                if (!response.trim().equals("failed")) {

                    Toast.makeText(getContext(), "Non Faculty enrolled for Donoation", Toast.LENGTH_SHORT).show();

                } else {
//                    stud_list.setAdapter(null);

                    Toast.makeText(getContext(), "Something Went Wrong !", Toast.LENGTH_SHORT).show();
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
                map.put("key", "enrollblooddonor");
                map.put("reg_id", reg_id);
                map.put("gender", "irrelevant");
                map.put("type", "Non Faculty");
                map.put("bldgrp", bloodarray[ipos]);
                map.put("name", namearray[ipos]);
                map.put("contact",contactarray[ipos]);
                map.put("address",addressarray[ipos]);

                return map;
            }
        };
        queue.add(request);
    }
}
