package com.kemosabe.bloodstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button loginbtn;
    TextView searchtxt;
    EditText uname, upass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = findViewById(R.id.btnlogin);
        searchtxt = findViewById(R.id.searchbtn);
        uname = findViewById(R.id.logemail);
        upass = findViewById(R.id.logpass);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uname.getText().toString().trim().equals("admin") && upass.getText().toString().trim().equals("admin123")) {

                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();

                }


            }
        });

        searchtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Searching Blood Groups", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), BloodGroupActivity.class));

            }
        });
    }
}
