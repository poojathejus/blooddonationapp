package com.kemosabe.bloodstore.ui.enrolldonorstudent;

import android.content.Intent;
import android.os.Bundle;
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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

public class DonorStudentFragment extends Fragment implements View.OnClickListener {


    //qr code scanner object
    private IntentIntegrator qrScan;
    ImageView scanQRbtn;
    EditText reg_id, dob, course, batch, name, address, contact, bloodgrp;
    Button getstudbtn, donorenrollbtn;
    RadioButton male, female;
    String respoarray[], searchstud_id[];


    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_donorstudent, container, false);
        slideshowViewModel.getText().observe(this, new Observer<String>() {

            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        scanQRbtn = root.findViewById(R.id.qrscanbtn);

        reg_id = root.findViewById(R.id.donor_reg_id);
        getstudbtn = root.findViewById(R.id.donsearch);

        dob = root.findViewById(R.id.donor_reg_dob);
        course = root.findViewById(R.id.donor_reg__course);
        batch = root.findViewById(R.id.donor_reg__batch);
        name = root.findViewById(R.id.donor_reg_name);
        address = root.findViewById(R.id.donor_reg_address);
        contact = root.findViewById(R.id.donor_reg_phone);
        bloodgrp = root.findViewById(R.id.donor_reg_blood_grp);
        donorenrollbtn = root.findViewById(R.id.donor_reg_enrollbtn);

        male = root.findViewById(R.id.radioM);
        female = root.findViewById(R.id.radioF);

        male.setClickable(false);
        female.setClickable(false);

        donorenrollbtn.setVisibility(View.INVISIBLE);


        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        scanQRbtn.startAnimation(animation);


        qrScan = IntentIntegrator.forSupportFragment(DonorStudentFragment.this);
        qrScan.setOrientationLocked(true);


        scanQRbtn.setOnClickListener(this);


        getstudbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if (!reg_id.getText().toString().contains("KH-SC-#00")) {
                        Toast.makeText(getContext(), "Incorrect ID format", Toast.LENGTH_SHORT).show();
                    } else {

                        searchstud_id = reg_id.getText().toString().split("00");


                        getStudentDetails(searchstud_id[1].trim());

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


//                Toast.makeText(getActivity(), scanContent + "   type:" + scanFormat, Toast.LENGTH_SHORT).show();

                Log.d("***", scanContent);


                reg_id.setText(scanContent.trim());


                String searchstud_id[] = scanContent.split("00");

                getStudentDetails(searchstud_id[1].trim());

            } catch (Exception e) {

                Log.d("***", "" + e);

            }


        } else {

            Toast.makeText(getActivity(), "Nothing scanned", Toast.LENGTH_SHORT).show();

        }
    }


    public void getStudentDetails(final String stud_id) {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                if (response.trim().equals("nullarray")) {

                    name.setText("");
                    dob.setText("");
                    course.setText("");
                    batch.setText("");
                    address.setText("");
                    contact.setText("");
                    bloodgrp.setText("");

                    Toast.makeText(getActivity(), "No Results", Toast.LENGTH_SHORT).show();

                } else {

                    donorenrollbtn.setVisibility(View.VISIBLE);


                    respoarray = response.split("#");


                    searchstud_id = reg_id.getText().toString().split("00");

                    name.setText(respoarray[0]);
                    dob.setText(respoarray[2]);
                    course.setText(respoarray[3]);
                    batch.setText(respoarray[4]);
                    address.setText(respoarray[6]);
                    contact.setText(respoarray[7]);
                    bloodgrp.setText(respoarray[5]);


                    if (respoarray[1].equals("Female")) {

                        female.setChecked(true);
                        male.setChecked(false);


                    } else if (respoarray[1].equals("Male")) {
                        male.setChecked(true);
                        female.setChecked(false);

                    }

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

                map.put("key", "getStudentbyId");
                map.put("stud_id", stud_id);

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
                    course.setText("");
                    batch.setText("");
                    address.setText("");
                    contact.setText("");
                    bloodgrp.setText("");
                    donorenrollbtn.setVisibility(View.INVISIBLE);

                    male.setChecked(false);
                    female.setChecked(false);

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
                map.put("gender", respoarray[1].trim());
                map.put("bldgrp", respoarray[5].trim());
                map.put("type", "student");
                map.put("name", name.getText().toString());
                map.put("contact", contact.getText().toString());
                map.put("address",address.getText().toString());

                return map;
            }
        };
        queue.add(request);


    }

}