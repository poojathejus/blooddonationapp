package com.kemosabe.bloodstore.ui.facultyreg;


import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.WriterException;
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;

import static android.content.Context.WINDOW_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragFacultyReg extends Fragment {


    EditText name, dob, address, phoneno;
    Spinner department, bloodgrp;
    Button bt;
    String sid, sname, sdob, sdepartment, saddress, sphoneno, sbloodgrp;
    final Calendar myCalendar = Calendar.getInstance();

    String[] dept = {"---department---", "computer science", "visual media", "commerce"};

    String[] bg = {"---Blood Group---", "A +ve", "B +ve", " AB +ve", " O +ve", " A -ve", " B -ve", " AB -ve", " O -ve"};

    ImageView QRimg;
    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    ImageView qrImage;
    String TAG = "logman";
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/BloodStore/";
    int QRsavedstate = 0;

    public FragFacultyReg() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_frag_faculty_reg, container, false);


        name = root.findViewById(R.id.edt_name);
        dob = root.findViewById(R.id.edt_dob);
        address = root.findViewById(R.id.edt_addres);
        phoneno = root.findViewById(R.id.edt_phone);
        department = root.findViewById(R.id.spin_department);
        bloodgrp = root.findViewById(R.id.spin_bldgrp);
        bt = root.findViewById(R.id.enrollstaff);
        QRimg = root.findViewById(R.id.qrcodeFac);

        ArrayAdapter spindepartment = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, dept);
        department.setAdapter(spindepartment);

        ArrayAdapter spinbloodgrp = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, bg);

        bloodgrp.setAdapter(spinbloodgrp);
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


                sname = name.getText().toString();
                sdob = dob.getText().toString();
                sdepartment = department.getSelectedItem().toString();
                saddress = address.getText().toString();
                sphoneno = phoneno.getText().toString();
                sbloodgrp = bloodgrp.getSelectedItem().toString();

                //   Toast.makeText(getContext(), "" + saddress, Toast.LENGTH_SHORT).show();
                try {


                    if (sname.isEmpty()) {
                        name.setError("Required");
                    } else if (sdepartment.equals("---department---")) {

                        Toast.makeText(getContext(), "Select dept", Toast.LENGTH_SHORT).show();
                    }
                    else if (sdob.isEmpty()) {

                        Toast.makeText(getContext(), "Select dob", Toast.LENGTH_SHORT).show();
                    }else if (saddress.isEmpty()) {
                        address.setError("Required");

                    } else if (sphoneno.isEmpty() || !sphoneno.matches("[1-9][0-9]{9}")) {
                        phoneno.setError("Required");

                    } else if (sbloodgrp.equals("---Blood Group---")) {

                        Toast.makeText(getContext(), "Select Blood Group", Toast.LENGTH_SHORT).show();


                    } else {

//                        Toast.makeText(getContext(), "Enrolling Student to Server...", Toast.LENGTH_LONG).show();
//                        Toast.makeText(getContext(), sname + " " + sdob + " " + sdepartment + " " + saddress + " " + sphoneno + " " + sbloodgrp, Toast.LENGTH_LONG).show();
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

                String[] respoarray = response.split("#");

                if (respoarray[0].trim().equals("success")) {


                    Toast.makeText(getContext(), "Enrollment Success ", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    dob.setText("");
                    address.setText("");
                    phoneno.setText("");

                    String staffid = respoarray[1].trim();

                    //Generating QR code
                    QRcode(staffid);

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
                map.put("key", "staff_enroll");
                map.put("name", sname);
                map.put("dob", sdob);
                map.put("department", sdepartment);
                map.put("address", saddress);
                map.put("phoneno", sphoneno);
                map.put("bloodgrp", sbloodgrp);


                return map;
            }
        };
        queue.add(request);
    }

    public void QRcode(String SenfacID) {


//        QRGEncoder qrgEncoder;
//        Bitmap bitmap;
//        String TAG = "logman";


        if (SenfacID.length() > 0) {
            WindowManager manager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;


            qrgEncoder = new QRGEncoder("KH-FC-#00" + SenfacID.trim(), null, QRGContents.Type.TEXT, smallerDimension);

            try {
                // Getting QR-Code as Bitmap
                bitmap = qrgEncoder.encodeAsBitmap();

                // Setting Bitmap to ImageView
                QRimg.setVisibility(View.VISIBLE);
                QRimg.setImageBitmap(bitmap);


//                scrollView.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
//                    }
//                });

                SaveQR(bitmap);

            } catch (WriterException e) {
                Log.v(TAG, e.toString());
            }
        }

    }

    public void SaveQR(Bitmap qrbitmap) {
        boolean save;
        String result;
        try {

            save = QRGSaver.save(savePath, "BloodStore" + randomIdentifier().trim(), qrbitmap, QRGContents.ImageType.IMAGE_JPEG);
            result = save ? "QR Code Saved" : "Image Not Saved";

            Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();

            QRsavedstate = 1;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // class variable
    final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final java.util.Random rand = new java.util.Random();

    // consider using a Map<String,Boolean> to say whether the identifier is being used or not
    final Set<String> identifiers = new HashSet<String>();

    public String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while (builder.toString().length() == 0) {
            int length = rand.nextInt(5) + 5;
            for (int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }
}
