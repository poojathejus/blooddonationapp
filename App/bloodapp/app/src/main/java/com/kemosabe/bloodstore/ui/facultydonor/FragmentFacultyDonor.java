package com.kemosabe.bloodstore.ui.facultydonor;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Utility;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFacultyDonor extends Fragment implements View.OnClickListener {


    //qr code scanner object
    private IntentIntegrator qrScan;
    ImageView scanQRbtn;
    EditText reg_id, dob, dept, name, address, contact, bloodgrp;
    Button getstudbtn, donorenrollbtn;
    String respoarray[], searchstud_id[];


    public FragmentFacultyDonor() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_faculty_donor, container, false);

        scanQRbtn = root.findViewById(R.id.Fqrscanbtn);
        reg_id = root.findViewById(R.id.Fdonor_reg_id);

        getstudbtn = root.findViewById(R.id.Fdonsearch);

        dob = root.findViewById(R.id.Fdonor_reg_dob);
        dept = root.findViewById(R.id.Fdonor_reg__dept);
        name = root.findViewById(R.id.Fdonor_reg_name);
        address = root.findViewById(R.id.Fdonor_reg_address);
        contact = root.findViewById(R.id.Fdonor_reg_phone);
        bloodgrp = root.findViewById(R.id.Fdonor_reg_blood_grp);

        donorenrollbtn = root.findViewById(R.id.Fdonor_reg_enrollbtn);

        donorenrollbtn.setVisibility(View.INVISIBLE);

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        scanQRbtn.startAnimation(animation);


        qrScan = IntentIntegrator.forSupportFragment(FragmentFacultyDonor.this);
        qrScan.setOrientationLocked(true);


        scanQRbtn.setOnClickListener(this);


        getstudbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!reg_id.getText().toString().contains("KH-FC-#00")) {
                        Toast.makeText(getContext(), "Incorrect ID format", Toast.LENGTH_SHORT).show();

                        reg_id.setText("");
                        name.setText("");
                        dob.setText("");
                        dept.setText("");
                        address.setText("");
                        contact.setText("");
                        bloodgrp.setText("");
                    } else {

                        searchstud_id = reg_id.getText().toString().split("00");


                        getFacultyDetails(searchstud_id[1].trim());

                    }
                } catch (Exception e) {

                    Log.d("***", "" + e);
                }


            }
        });

        donorenrollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                enrollDonor(searchstud_id[1]);
            }
        });


        return root;
    }

    @Override
    public void onClick(View view) {

        qrScan.initiateScan();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (scanningResult != null) {

            String scanContent = null;

            String scanFormat = null;
            if (scanningResult.getContents() != null) {

                scanContent = scanningResult.getContents();
                scanFormat = scanningResult.getFormatName();
            }

            try {
                if (scanContent.contains("KH-FC")) {

                    Log.d("***", scanContent);


                    Toast.makeText(getActivity(), scanContent + "   type:" + scanFormat, Toast.LENGTH_SHORT).show();
                    reg_id.setText(scanContent.trim());

                    String searchfaculty_id[] = scanContent.split("00");

                    getFacultyDetails(searchfaculty_id[1].trim());


                } else {
                    reg_id.setText("");
                }


            } catch (Exception e) {

                Log.d("***", "" + e);

            }


        } else {

            Toast.makeText(getActivity(), "Nothing scanned", Toast.LENGTH_SHORT).show();

        }
    }


    public void getFacultyDetails(final String stud_id) {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                if (response.trim().equals("nullarray")) {

                    name.setText("");
                    dob.setText("");
                    dept.setText("");
                    address.setText("");
                    contact.setText("");
                    bloodgrp.setText("");

                    Toast.makeText(getActivity(), "No Results", Toast.LENGTH_SHORT).show();

                } else {

                    donorenrollbtn.setVisibility(View.VISIBLE);


                    respoarray = response.split("#");


                    searchstud_id = reg_id.getText().toString().split("00");

                    name.setText(respoarray[1]);
                    dob.setText(respoarray[2]);
                    dept.setText(respoarray[3]);
                    address.setText(respoarray[4]);
                    contact.setText(respoarray[5]);
                    bloodgrp.setText(respoarray[6]);


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

                map.put("key", "getFacultybyId");
                map.put("fac_id", stud_id);

                return map;
            }
        };
        queue.add(request);


    }

    public void enrollDonor(final String stud_id) {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                if (!response.trim().equals("failed")) {


                    Toast.makeText(getActivity(), "Enrolled as Donor", Toast.LENGTH_SHORT).show();

                    reg_id.setText("");
                    name.setText("");
                    dob.setText("");
                    dept.setText("");
                    address.setText("");
                    contact.setText("");
                    bloodgrp.setText("");

                    donorenrollbtn.setVisibility(View.INVISIBLE);


                } else {

                    Toast.makeText(getActivity(), "Something went wrong !", Toast.LENGTH_SHORT).show();

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

                map.put("key", "enrollblooddonor");
                map.put("reg_id", stud_id);
                map.put("gender", "irrelevant");
                map.put("bldgrp", respoarray[6].trim());
                map.put("type", "faculty");
                map.put("name", name.getText().toString());
               map.put("contact", contact.getText().toString());
               map.put("address",address.getText().toString());



                return map;
            }
        };
        queue.add(request);


    }

}
