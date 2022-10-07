package com.kemosabe.bloodstore.ui.searchbyid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.kemosabe.bloodstore.Idnameactivity;
import com.kemosabe.bloodstore.MyListView;
import com.kemosabe.bloodstore.R;
import com.kemosabe.bloodstore.Searchactivity;
import com.kemosabe.bloodstore.Searchbloodadapter;
import com.kemosabe.bloodstore.Utility;

import java.util.HashMap;
import java.util.Map;

public class SearhByIdFragment extends Fragment {

    RadioGroup rg1;
    // RadioButton rid,rname;
    EditText edt;
    Button bt;
    String Searchword, SearchCat;
    ListView searhlist;

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_searchbyid, container, false);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {


                searhlist = root.findViewById(R.id.searchlist);

                bt = root.findViewById(R.id.searchby);
                edt = root.findViewById(R.id.enter);
                rg1 = root.findViewById(R.id.radiogroup);
                // rid=root.findViewById(R.id.id);
                // rname=root.findViewById(R.id.name);


                bt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int select = rg1.getCheckedRadioButtonId();

                        if (select == -1) {
                            Toast.makeText(getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
                        } else {

                            RadioButton radioButton = rg1.findViewById(select);
                            Searchword = edt.getText().toString().toUpperCase().trim();
                            SearchCat = radioButton.getText().toString().trim();
                            edt.setText(Searchword);

                            if (Searchword.equals("")) {
                                Toast.makeText(getContext(), "Enter Search Item", Toast.LENGTH_SHORT).show();

                            } else {

                                Searchbycategory();

                            }

                        }
                    }
                });


            }
        });


        return root;
    }

    public void Searchbycategory() {


        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getContext());

        StringRequest request = new StringRequest(Request.Method.POST, Utility.url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("******", response);

                if (response.trim().equals("nullarray")) {

                    Toast.makeText(getActivity(), "No Results", Toast.LENGTH_SHORT).show();
                    searhlist.setAdapter(null);

                } else {

                    String respoarray[] = response.split("#");

 //             String[] stname, stcourse, stbatch, stgender;
 //                   stname = respoarray[0].split(":");
//                    stcourse = respoarray[1].split(":");
//                    stbatch = respoarray[2].split(":");
//                    stgender = respoarray[3].split(":");


                    String[] stbg,stphono,stcat,stname;
                    stbg = respoarray[0].split(":");
                    stphono = respoarray[1].split(":");
                    stcat=respoarray[2].split(":");
                    stname=respoarray[3].split(":");
//
//                    MyListView myListView = new MyListView(getActivity(), stname, stcourse, stbatch, stgender);
//                    searhlist.setAdapter(myListView);

                    Idnameactivity myListView= new Idnameactivity(getActivity(),stbg,stphono,stcat,stname);
                    searhlist.setAdapter(myListView);


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
                map.put("key", "student_search_category");
                map.put("searchcat", SearchCat.trim());
                map.put("searchword", Searchword.trim());

                return map;
            }
        };
        queue.add(request);
    }
}