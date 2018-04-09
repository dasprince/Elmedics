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

        prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorHome.this , DoctorPrescription.class));
                finish();
            }
        });
    }
}
