package com.kemosabe.bloodstore.ui.enroll_student;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

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

import static android.content.Context.RECEIVER_VISIBLE_TO_INSTANT_APPS;
import static android.content.Context.WINDOW_SERVICE;

public class EnrollStudent extends Fragment {

    ScrollView scrollView;
    EditText edtname, edtdob, edtemail, edtphone, edtaddress;
    String sedtname, sedtdob, sedtemail, sedtphone, sedtaddress, sCourse, sBatch, sBlood = "koko", sGender="Male";
    Button enrollbtn;
    Spinner coursespin, batchspin, bloodgrpspin;
    RadioGroup gendergrp;
    RadioButton genderbtn;
    ImageView QRimg;

    String[] Courselist = {"Select Course", "MCA", "BCA", "BSc.Computer Science", "BSc.Mathematics", "BSc.Visual Media"};
    String[] Semlist = {"Semester", "I", "II", "III", "IV", "V", "VI"};
    String[] Batchlist = {"Batch", "2016-2019", "2017-2020", "2016-2021", "2019-2022"};
    String[] Bldgrplist = {"---Blood Group---", "A +ve", "B +ve", " AB +ve", " O +ve", " A -ve", " B -ve", " AB -ve", " O -ve"};

    QRGEncoder qrgEncoder;
    Bitmap bitmap;
    ImageView qrImage;
    String TAG = "logman";
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/BloodStore/";
    int QRsavedstate = 0;

    final Calendar myCalendar = Calendar.getInstance();

    private com.kemosabe.bloodstore.ui.enroll_student.enrollstudentViewModel enrollstudentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        enrollstudentViewModel = ViewModelProviders.of(this)
                .get(com.kemosabe.bloodstore.ui.enroll_student.enrollstudentViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_enroll, container, false);


        scrollView = root.findViewById(R.id.scrolly);

        coursespin = root.findViewById(R.id.spin_course);
        batchspin = root.findViewById(R.id.spin_batch);
        bloodgrpspin = root.findViewById(R.id.spin_bldgrp);
        gendergrp = root.findViewById(R.id.radioGrp);
        QRimg = root.findViewById(R.id.qrcode);

        enrollbtn = root.findViewById(R.id.enrollbtn);
        edtname = root.findViewById(R.id.edt_name);
        edtdob = root.findViewById(R.id.edt_dob);
        edtemail = root.findViewById(R.id.edt_mail);
        edtphone = root.findViewById(R.id.edt_phone);
        edtaddress = root.findViewById(R.id.edt_address);


        ArrayAdapter courseadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Courselist);

        coursespin.setAdapter(courseadapter);

        ArrayAdapter batchadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Batchlist);

        batchspin.setAdapter(batchadapter);

        ArrayAdapter bldgrpadapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, Bldgrplist);

        bloodgrpspin.setAdapter(bldgrpadapter);


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
        edtdob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(getActivity(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        gendergrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                genderbtn = root.findViewById(i);
                sGender = genderbtn.getText().toString().trim();
                Log.d("******", sGender);

            }
        });


        enrollbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sedtname = edtname.getText().toString().trim().toUpperCase();
                sedtdob = edtdob.getText().toString().trim();
                sedtemail = edtemail.getText().toString().trim();
                sedtphone = edtphone.getText().toString().trim();
                sedtaddress = edtaddress.getText().toString().trim().toUpperCase();

                sCourse = coursespin.getSelectedItem().toString();
                sBatch = batchspin.getSelectedItem().toString();
                sBlood = bloodgrpspin.getSelectedItem().toString();

           //    Toast.makeText(getContext(), "gender is" + sGender, Toast.LENGTH_SHORT).show();

                try {


                    if (sedtname.isEmpty()) {
                        edtname.setError("Required");

                    } else if (sedtdob.isEmpty()) {
                        edtdob.setError("Required");

                    } else if (sedtemail.isEmpty() || !sedtemail.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                        edtemail.setError("Required");

                    } else if (sedtphone.isEmpty() || !sedtphone.matches("[1-9][0-9]{9}")) {
                        edtphone.setError("Required");

                    } else if (sedtaddress.isEmpty()) {
                        edtaddress.setError("Required");

                    } else if (sCourse.equals("Select Course")) {

                        Toast.makeText(getContext(), "Select Course", Toast.LENGTH_SHORT).show();

                    } else if (sBatch.equals("Batch")) {

                        Toast.makeText(getContext(), "Select Batch", Toast.LENGTH_SHORT).show();

                    } else if (sBlood.equals("---Blood Group---")) {

                        Toast.makeText(getContext(), "Select Blood Group", Toast.LENGTH_SHORT).show();

                    } else if (sGender.isEmpty()) {

                        Toast.makeText(getContext(), "Choose Gender", Toast.LENGTH_SHORT).show();

                    } else {

                        Toast.makeText(getContext(), "Enrolling Student to Server...", Toast.LENGTH_LONG).show();


                        Volley_enroll_student();

//                    enrollbtn.setEnabled(false);
                        enrollbtn.setBackgroundColor(Color.BLACK);
                    }
                } catch (Exception e) {
                    Log.d("errory", e + "");
                }

            }
        });
        return root;
    }

    public void Volley_enroll_student() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                String respoarray[] = response.split("#");

                if (respoarray[0].trim().equals("success")) {

                    String enroll_ID = respoarray[1].trim();

//                    Toast.makeText(getContext(), "Enrollment Success " + enroll_ID, Toast.LENGTH_SHORT).show();

//                    enrollbtn.setVisibility(View.INVISIBLE);

                    QRcode(enroll_ID);


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
                map.put("key", "student_enroll");
                map.put("name", sedtname);
                map.put("dob", sedtdob);
                map.put("email", sedtemail);
                map.put("phone", sedtphone);
                map.put("address", sedtaddress);
                map.put("course", sCourse);
                map.put("batch", sBatch);
                map.put("blood", sBlood);
                map.put("gender", sGender);

                return map;
            }
        };
        queue.add(request);
    }


    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edtdob.setText(sdf.format(myCalendar.getTime()));
    }


    public void QRcode(String SenrollID) {


//        QRGEncoder qrgEncoder;
//        Bitmap bitmap;
//        String TAG = "logman";


        if (SenrollID.length() > 0) {
            WindowManager manager = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
            Display display = manager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;
            int smallerDimension = width < height ? width : height;
            smallerDimension = smallerDimension * 3 / 4;


            qrgEncoder = new QRGEncoder("KH-SC-#00" + SenrollID.trim(), null, QRGContents.Type.TEXT, smallerDimension);

            try {
                // Getting QR-Code as Bitmap
                bitmap = qrgEncoder.encodeAsBitmap();

                // Setting Bitmap to ImageView
                QRimg.setVisibility(View.VISIBLE);
                QRimg.setImageBitmap(bitmap);


                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(ScrollView.FOCUS_DOWN);
                    }
                });

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