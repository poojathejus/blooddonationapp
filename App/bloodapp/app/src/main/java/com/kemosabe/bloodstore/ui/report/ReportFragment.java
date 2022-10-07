package com.kemosabe.bloodstore.ui.report;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Utility;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReportFragment extends Fragment {

    private ShareViewModel shareViewModel;


    Button oldreportbtn, getreportdates;
    Spinner donatedatespinner;
    LinearLayout reportlayout;
    ListView viewdonorslist;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        shareViewModel = ViewModelProviders.of(this).get(ShareViewModel.class);

        View root = inflater.inflate(R.layout.fragment_report, container, false);


        oldreportbtn = root.findViewById(R.id.oldreportbtn);
        getreportdates = root.findViewById(R.id.donationdate);
        donatedatespinner = root.findViewById(R.id.donationdatespin);
        viewdonorslist = root.findViewById(R.id.listviewdonors);
        reportlayout = root.findViewById(R.id.spinlayoutreport);


        oldreportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                File file = new File((getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)) + "/" + "aaabbbccc.xls");

                Uri path = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file);

                Log.d("***", file + " <----FILE");
                Log.d("***", path + "<----PATH");

                Intent excelIntent = new Intent(Intent.ACTION_VIEW);

                excelIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                excelIntent.setDataAndType(path, "application/vnd.ms-excel");

//                excelIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                try {

                    startActivity(excelIntent);

                } catch (ActivityNotFoundException e) {

                    Toast.makeText(getContext(), "No Application available to viewExcel", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getreportdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Volley_getdonationdates();

            }
        });

        return root;
    }

    public void Volley_getdonationdates() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                String respoarray[] = response.split("#");

                if (respoarray[0].trim().equals("success")) {


                    final String donationdate[] = respoarray[1].split(":");

                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, donationdate);

                    reportlayout.setVisibility(View.VISIBLE);

                    donatedatespinner.setAdapter(adapter);


                    donatedatespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            Volley_getdatewiselist(donationdate[i].trim());
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

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
                map.put("key", "getdonationdates");
                return map;
            }
        };
        queue.add(request);
    }

    public void Volley_getdatewiselist(final String date) {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                String respoarray[] = response.split("#");

                if (respoarray[0].trim().equals("success")) {


                    String donordetails[] = respoarray[1].split(":");

                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, donordetails);


//                    adapter.clear();
                    viewdonorslist.setAdapter(adapter);


                } else {
                    Toast.makeText(getContext(), "Failed to Fetch Details", Toast.LENGTH_LONG).show();

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
                map.put("key", "getdonationlistdatewise");
                map.put("date", date);
                return map;
            }
        };
        queue.add(request);
    }
}