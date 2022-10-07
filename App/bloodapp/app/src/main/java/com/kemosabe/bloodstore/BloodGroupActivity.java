package com.kemosabe.bloodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class BloodGroupActivity extends AppCompatActivity {

    Spinner bloodspinner;
    String[] Bldgrplist = {"---Blood Group---", "A +ve", "B +ve", " AB +ve", " O +ve", " A -ve", " B -ve", " AB -ve", " O -ve"};
    ListView viewdonors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_group);

        bloodspinner = findViewById(R.id.groupspin);
        viewdonors = findViewById(R.id.searchbldlist);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, Bldgrplist);

        bloodspinner.setAdapter(adapter);


        bloodspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


           //     Toast.makeText(BloodGroupActivity.this, "searching for " + Bldgrplist[i] + " Blood Donors", Toast.LENGTH_LONG).show();

                Volley_search_students(Bldgrplist[i].trim());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public void Volley_search_students(final String bloodname) {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);


                if (!response.trim().equals("failed")) {


                    String respoarray[] = response.split("#");

                    String[] stname, stcourse, stbatch, stgender, stphone, staddress;

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
                    stphone = respoarray[1].split(":");
//                    stcourse = respoarray[1].split(":");
 //                   stbatch = respoarray[2].split(":");
                    staddress = respoarray[2].split(":");
//                    stgender = respoarray[3].split(":");


                    Searchactivity myListView = new Searchactivity(BloodGroupActivity.this, stname, stphone,staddress);
                    viewdonors.setAdapter(myListView);

                } else {
                    viewdonors.setAdapter(null);

                   // Toast.makeText(getApplicationContext(), "No Students Found", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), "my Error :" + error, Toast.LENGTH_LONG).show();
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
