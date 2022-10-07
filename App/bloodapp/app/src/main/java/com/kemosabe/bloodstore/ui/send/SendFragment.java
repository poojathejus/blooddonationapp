package com.kemosabe.bloodstore.ui.send;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SendFragment extends Fragment {
    EditText edtvalue;
    EditText stname, stmail, staddress, stphno, stdob;
    EditText sfname, sfaddress, sfphno, sfdob;
    EditText nodob, noname, noaddress, nophno;
    String usertype = "";
    Button enter, upd;
    TextView textnonbg;

    String enoname, enodob, enobg, enoadd, enophno;
    Spinner stbatch, stcourse, stbg;
    Spinner sfdepartment, sfbg;
    Spinner nobg;

    RadioGroup categoryrdgrp, stdrdgrp;
    RadioButton tempcatbutt, strb;

    String stgender, st1, edittextval;
    LinearLayout studentlay, radio, stafflay, nonstafflay;
    TextView txtcourse, txtbatch, txtbdgp, txtgen;
    TextView txtstaffdept, txtstaffbg,txtnonstaffbg;

    String estname, estcourse, estbatch, estdob, estbdgrp, estgen, estmail, estphno, estaddress, stcou;

    String esfname, esfdept, esfdob, esfbg, esfphno, esfad;
    String category;

    // final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar = Calendar.getInstance();


    String[] course = {"Select Course", "MCA", "BCA", "BSc.Computer Science", "BSc.Mathematics", "BSc.Visual Media"};
    String[] batch = {"Batch", "2016-2019", "2017-2020", "2016-2021", "2019-2022"};
    String[] bglist = {"---Blood Group---", "A +ve", "B +ve", " AB +ve", " O +ve", " A -ve", " B -ve", " AB -ve", " O -ve"};
    String[] department = {"--Department--", "Computer Science", "Commerce", "English", "Mathematics", "Visual Media"};


    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sendViewModel = ViewModelProviders.of(this).get(SendViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView = root.findViewById(R.id.text_send);

        try {


            radio = root.findViewById(R.id.radiolyt);
            txtgen = root.findViewById(R.id.stgen);
            txtbdgp = root.findViewById(R.id.bdgp);
            txtbatch = root.findViewById(R.id.batch);
            txtcourse = root.findViewById(R.id.course);
            txtnonstaffbg=root.findViewById(R.id.textviewnonstaffbg);
            studentlay = root.findViewById(R.id.studlyt);
            txtstaffdept = root.findViewById(R.id.textviewdep);
            textnonbg=root.findViewById(R.id.textviewnonstaffbg);
            txtstaffbg = root.findViewById(R.id.textviewstaffbg);
            stafflay = root.findViewById(R.id.stafflyut);

            nonstafflay = root.findViewById(R.id.nonstafflyt);

            edtvalue = root.findViewById(R.id.value);
            stname = root.findViewById(R.id.studentname);
            stmail = root.findViewById(R.id.studentmail);
            staddress = root.findViewById(R.id.studentaddress);
            stphno = root.findViewById(R.id.studentphone);
            sfname = root.findViewById(R.id.staffname);
            sfaddress = root.findViewById(R.id.staffaddress);
            sfphno = root.findViewById(R.id.staffphone);
            noname = root.findViewById(R.id.nonname);
            noaddress = root.findViewById(R.id.nonaddres);
            nophno = root.findViewById(R.id.nonphone);
            upd = root.findViewById(R.id.updatedetails);
            enter = root.findViewById(R.id.search);
            stdob = root.findViewById(R.id.studentdob);
            sfdob = root.findViewById(R.id.staffdob);
            nodob = root.findViewById(R.id.nondob);
            categoryrdgrp = root.findViewById(R.id.update);
            stdrdgrp = root.findViewById(R.id.studentradioGrp);
            edtvalue = root.findViewById(R.id.value);
            stname = root.findViewById(R.id.studentname);
            staddress = root.findViewById(R.id.studentaddress);
            stphno = root.findViewById(R.id.studentphone);
            sfname = root.findViewById(R.id.staffname);
            sfaddress = root.findViewById(R.id.staffaddress);
            sfphno = root.findViewById(R.id.staffphone);
            noname = root.findViewById(R.id.nonname);
            noaddress = root.findViewById(R.id.nonaddres);
            nophno = root.findViewById(R.id.nonphone);
            upd = root.findViewById(R.id.updatedetails);
            enter = root.findViewById(R.id.search);
            //stdob = root.findViewById(R.id.studentdob);
            // sfdob = root.findViewById(R.id.staffdob);
            //nodob = root.findViewById(R.id.nondob);
            stbatch = root.findViewById(R.id.studentbatch);
            stcourse = root.findViewById(R.id.studentcourse);
            stbg = root.findViewById(R.id.studentbldgrp);
            sfdepartment = root.findViewById(R.id.staffdepartment);
            sfbg = root.findViewById(R.id.staffbldgrp);
            nobg = root.findViewById(R.id.nonbldgrp);

        } catch (Exception amritha) {
            System.out.println(amritha);
            Log.d("checkhere", "" + amritha);
        }
        ArrayAdapter scourse = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, course);

        stcourse.setAdapter(scourse);

//      stcourse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                txtcourse.setText(course[position]);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });


        ArrayAdapter sbatch = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, batch);

        stbatch.setAdapter(sbatch);

        ArrayAdapter sbg = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, bglist);

        stbg.setAdapter(sbg);

        ArrayAdapter staffdep = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, department);

        sfdepartment.setAdapter(staffdep);

        final ArrayAdapter stbloodgroup = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, bglist);

        sfbg.setAdapter(stbloodgroup);

        ArrayAdapter nonstaffbg = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, bglist);

        nobg.setAdapter(nonstaffbg);

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

        stdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        sfdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        nodob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        stdrdgrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                strb = root.findViewById(checkedId);
                estgen = strb.getText().toString();
            }
        });


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    String category = tempcatbutt.getText().toString().trim();


                int categoryid = categoryrdgrp.getCheckedRadioButtonId();
                if(categoryid == -1)
                {
                    Toast.makeText(getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    edittextval = edtvalue.getText().toString().trim();
                    tempcatbutt = root.findViewById(categoryid);
                    category = tempcatbutt.getText().toString().trim();

                    if(edittextval.equals(""))
                    {
                        Toast.makeText(getContext(), "Enter value", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Volley_getdetails(category);
                    }
                }




            }
        });


        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(usertype.equals("student")) {
                    estname = stname.getText().toString().trim();
                    estcourse = stcourse.getSelectedItem().toString().trim();
                    //  estcourse=txtcourse.getText().toString();
                    estbatch = stbatch.getSelectedItem().toString().trim();
                    estdob = stdob.getText().toString().trim();
                    estbdgrp = stbg.getSelectedItem().toString().trim();

                    estmail = stmail.getText().toString().trim();
                    estphno = stphno.getText().toString().trim();
                    estaddress = staddress.getText().toString().trim();

                    try {

                        if (estcourse.equals("Select Course")) {

                            estcourse = txtcourse.getText().toString();

                        }
                        if (estbatch.equals("Batch")) {

                            estbatch = txtbatch.getText().toString();

                        }


                        if (estbdgrp.equals("---Blood Group---")) {

                            estbdgrp = txtbdgp.getText().toString();

                        }
                    } catch (Exception e) {

                        Log.d("***", e + "");
                    }


                    Log.d("*****", estgen);
                    Toast.makeText(getContext(), estname + estcourse + estbatch + estdob + estbdgrp + estgen + estmail + estphno + estaddress, Toast.LENGTH_SHORT).show();

                    Volley_updatestudent();
                }

                if(usertype.equals("Faculty"))
                {
                    esfname=sfname.getText().toString().trim();
                    esfdept=sfdepartment.getSelectedItem().toString().trim();
                    esfdob=sfdob.getText().toString().trim();
                    esfbg=sfbg.getSelectedItem().toString().trim();
                    esfphno=sfphno.getText().toString().trim();
                    esfad=sfaddress.getText().toString().trim();
                    try {
                        if (esfdept.equals("--Department--"))
                        {
                            esfdept = txtstaffdept.getText().toString();
                        }
                        if (esfbg.equals("---Blood Group---"))
                        {
                            esfbg = txtstaffbg.getText().toString();
                        }
                    }
                    catch (Exception e)
                    {

                        Log.d("***", "e");
                    }
                    Toast.makeText(getContext(), esfname + esfdept + esfdob + esfbg  + esfphno + esfad, Toast.LENGTH_SHORT).show();

                    Volley_updatestaff();
                }
                if(usertype.equals("Non Faculty"))
                {
                    enoname=noname.getText().toString().trim();
                    enodob=nodob.getText().toString().trim();
                    enobg=nobg.getSelectedItem().toString().trim();
                    enoadd=noaddress.getText().toString().trim();
                    enophno=nophno.getText().toString().trim();


                    try {

                        if (enobg.equals("---Blood Group---"))
                        {
                            enobg = txtnonstaffbg.getText().toString();
                        }
                    }
                    catch (Exception e)
                    {

                        Log.d("***",  "error**");
                    }
                    Toast.makeText(getContext(), enoname + enodob + enobg  + enophno + enoadd, Toast.LENGTH_SHORT).show();

                    Volley_updatenonstaff();


                }
            }
        });


        return root;
    }


    public void Volley_updatestudent() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (response.trim().equals("success")) {


                    Toast.makeText(getContext(), "success updated student details", Toast.LENGTH_SHORT).show();
                    studentlay.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Failed to Update", Toast.LENGTH_LONG).show();


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
                map.put("key", "studentupdate");
                map.put("id", edittextval);
                map.put("name", estname);
                map.put("course", estcourse);
                map.put("batch", estbatch);
                map.put("dob", estdob);
                map.put("bloodgrp", estbdgrp);
                map.put("gender", estgen);
                map.put("mail", estmail);
                map.put("phno", estphno);
                map.put("address", estaddress);


                return map;
            }
        };
        queue.add(request);
    }


    public void Volley_updatestaff() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (response.trim().equals("success")) {


                    Toast.makeText(getContext(), "success updated staff details", Toast.LENGTH_SHORT).show();
                    stafflay.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Failed to Update", Toast.LENGTH_LONG).show();


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
                map.put("key", "staffupdate");
                map.put("id", edittextval);
                map.put("name", esfname);
                map.put("department", esfdept);
                map.put("dob", esfdob);
                map.put("bloodgrp", esfbg);
                map.put("phno", esfphno);
                map.put("address", esfad);


                return map;
            }
        };
        queue.add(request);
    }

    public void Volley_updatenonstaff() {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);
                if (response.trim().equals("success")) {


                    Toast.makeText(getContext(), "success updated non-staff details", Toast.LENGTH_SHORT).show();
                    nonstafflay.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Failed to Update", Toast.LENGTH_LONG).show();


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
                map.put("key", "nonstaffupdate");
                map.put("id", edittextval);
                map.put("name", enoname);
                map.put("dob", enodob);
                map.put("bloodgrp", enobg);
                map.put("phno", enophno);
                map.put("address", enoadd);


                return map;
            }
        };
        queue.add(request);
    }


    public void Volley_getdetails(final String catname) {

        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******getid", response);
                String arr[] = response.split("#");


                usertype=arr[0].trim();
                if (arr[0].trim().equals("student")) {
                    studentlay.setVisibility(View.VISIBLE);
                    upd.setVisibility(View.VISIBLE);
                    radio.setVisibility(View.GONE);
                    stname.setText(arr[1]);
                    txtcourse.setText(arr[7]);
                    txtbatch.setText(arr[8]);
                    stdob.setText(arr[5]);
                    txtbdgp.setText(arr[6]);
                    txtgen.setText(arr[9]);
                    stmail.setText(arr[3]);
                    stphno.setText(arr[2]);
                    staddress.setText(arr[4]);

                    estgen = arr[9].trim();
                    estbdgrp = arr[6].trim();
                    estbatch = arr[8].trim();
                    estcourse = arr[7].trim();

                } else if (arr[0].trim().equals("Faculty")) {

                    stafflay.setVisibility(View.VISIBLE);
                    upd.setVisibility(View.VISIBLE);
                    radio.setVisibility(View.GONE);

                    sfname.setText(arr[1]);
                    txtstaffdept.setText(arr[2]);
                    sfdob.setText(arr[3]);
                    txtstaffbg.setText(arr[4]);
                    sfphno.setText(arr[5]);
                    sfaddress.setText(arr[6]);

                    esfbg = arr[6].trim();
                    esfdept = arr[2].trim();

                } else if (arr[0].trim().equals("Non Faculty")) {

                    nonstafflay.setVisibility(View.VISIBLE);
                    upd.setVisibility(View.VISIBLE);
                    radio.setVisibility(View.GONE);

                    noname.setText(arr[1]);
                    nodob.setText(arr[2]);
                    textnonbg.setText(arr[3]);
                    noaddress.setText(arr[5]);
                    nophno.setText(arr[4]);

                    enobg = arr[3].trim();
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
                map.put("key", "category");
                map.put("id", edittextval);
                map.put("categname", catname);


                return map;
            }
        };
        queue.add(request);
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        stdob.setText(sdf.format(myCalendar.getTime()));
        sfdob.setText(sdf.format(myCalendar.getTime()));
        nodob.setText(sdf.format(myCalendar.getTime()));
    }


}