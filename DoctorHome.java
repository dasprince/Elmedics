package com.example.hp.elmedics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorHome extends AppCompatActivity  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home);
        TextView doctorname = (TextView)findViewById(R.id.tvdocname);
        String docname = DoctorLogin.docname;
        doctorname.setText(docname);

        ImageView prescription = (ImageView)findViewById(R.id.ivprescriptiondoc);
        ImageView viewpatient = (ImageView)findViewById(R.id.viewpatientiv);
        ImageView myprofile = (ImageView)findViewById(R.id.ivmyprofile);

        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHome.this , DoctorPrescription.class));
                finish();
            }
        });

        viewpatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHome.this,ViewPatient.class));
                finish();
            }
        });

        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHome.this , MyProfileDoctor.class));
                finish();
            }
        });
    }
}
