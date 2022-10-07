package com.kemosabe.bloodstore.ui.nonstaffreg;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentNonStaffReg extends Fragment {


    EditText name, dob, address, phoneno;
    Spinner bloodgrp;
    Button bt;
    String nname, ndob, naddress, nphoneno, nbloodgrp;

    final Calendar myCalendar = Calendar.getInstance();

    String[] nbg = {"---Blood Group---", "A +ve", "B +ve", " AB +ve", " O +ve", " A -ve", " B -ve", " AB -ve", " O -ve"};


    public FragmentNonStaffReg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_non_staff_reg, container, false);


        name = root.findViewById(R.id.edt_nonname);
        dob = root.findViewById(R.id.edt_nondob);
        address = root.findViewById(R.id.edt_nonaddres);
        phoneno = root.findViewById(R.id.edt_nonphone);
        bloodgrp = root.findViewById(R.id.spin_nonbldgrp);
        bt = root.findViewById(R.id.enrollnon);

        ArrayAdapter nonbloodgrp = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, nbg);
        bloodgrp.setAdapter(nonbloodgrp);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };
        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                nname = name.getText().toString();
                ndob = dob.getText().toString();
                naddress = address.getText().toString();
                nphoneno = phoneno.getText().toString();
                nbloodgrp = bloodgrp.getSelectedItem().toString();

                //   Toast.makeText(getContext(), "" + saddress, Toast.LENGTH_SHORT).show();
                try {


                    if (nname.isEmpty()) {
                        name.setError("Required");
                    } else if (naddress.isEmpty()) {
                        address.setError("Required");

                    }
                    else if (ndob.isEmpty()) {
                        dob.setError("Required");
                    }
                    else if (nphoneno.isEmpty() || !nphoneno.matches("[1-9][0-9]{9}")) {
                        phoneno.setError("Required");

                    } else if (nbloodgrp.equals("---Blood Group---")) {

                        Toast.makeText(getContext(), "Select Blood Group", Toast.LENGTH_SHORT).show();


                    } else {

//                        Toast.makeText(getContext(), "Enrolling Student to Server...", Toast.LENGTH_LONG).show();
                        Toast.makeText(getContext(), nname + " " + ndob + " " + naddress + " " + nphoneno + " " + nbloodgrp, Toast.LENGTH_LONG).show();
                        //  Toast.makeText(getActivity(), sname, Toast.LENGTH_SHORT).show();


                        Volley_enroll_student();


//                    enrollbtn.setEnabled(false);
                        bt.setBackgroundColor(Color.BLACK);
                    }
                } catch (Exception e) {
                    Log.d("errory", e + "");
                }
            }
        });

        return root;

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dob.setText(sdf.format(myCalendar.getTime()));
    }

    public void Volley_enroll_student() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);


                if (response.trim().equals("success")) {


                    Toast.makeText(getContext(), "Enrollment Success ", Toast.LENGTH_SHORT).show();

//                    enrollbtn.setVisibility(View.INVISIBLE);

                    name.setText("");
                    dob.setText("");
                    address.setText("");
                    phoneno.setText("");


                } else {
                    Toast.makeText(getContext(), "Failed to Enroll", Toast.LENGTH_LONG).show();

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
                map.put("key", "nonstaff_enroll");
                map.put("name", nname);
                map.put("dob", ndob);
                map.put("address", naddress);
                map.put("phoneno", nphoneno);
                map.put("bloodgrp", nbloodgrp);


                return map;
            }
        };
        queue.add(request);
    }

}
