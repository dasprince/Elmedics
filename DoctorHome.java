package com.example.hp.elmedics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DoctorHome extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        TextView doctorname = (TextView)findViewById(R.id.docnametv);
        String docname = DoctorLogin.docname;
        doctorname.setText(docname);
    }
}
