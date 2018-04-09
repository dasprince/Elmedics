package com.example.hp.elmedics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DoctorPrescription extends AppCompatActivity {

    String docname = DoctorLogin.docname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_prescription);

        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        final String todaydate = df.format(date);



        final EditText patientnum = (EditText)findViewById(R.id.etpatientnum);
        final EditText medicinename = (EditText)findViewById(R.id.etmedname);
        final EditText dozes = (EditText)findViewById(R.id.ettimesaday);
        final EditText comments = (EditText)findViewById(R.id.etcomments);

        Button search = (Button)findViewById(R.id.btnpatientsearch);
        final Button submit = (Button)findViewById(R.id.btnsubmitprescription);

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        final DatabaseReference myref = firebase.getReference();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit.setEnabled(false);
                if(patientnum.getText().toString().matches("")){
                    Toast.makeText(DoctorPrescription.this, "Please enter patient's mobile number",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Users").hasChild(patientnum.getText().toString())){
                                //patient found
                                Toast.makeText(DoctorPrescription.this,"Patient found",Toast.LENGTH_SHORT)
                                        .show();
                                submit.setEnabled(true);

                            }
                            else{
                                //patient not found

                                Toast.makeText(DoctorPrescription.this,"Patient not found",Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(medicinename.getText().toString().matches("") || dozes.getText().toString().matches("") ){
                    Toast.makeText(DoctorPrescription.this, "Please enter medicine name / dozes",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(medicinename.getText().toString().matches("") || dozes.getText().toString().matches("")){
                                Toast.makeText(DoctorPrescription.this,"Please enter medicine name and dozes",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else{
                                myref.child("Users").child(patientnum.getText().toString()).child("prescription").child(todaydate)
                                        .child("medicines").setValue(medicinename.getText().toString());
                                myref.child("Users").child(patientnum.getText().toString()).child("prescription").child(todaydate)
                                        .child("dozes").setValue(dozes.getText().toString());
                                myref.child("Users").child(patientnum.getText().toString()).child("prescription").child(todaydate)
                                        .child("comments").setValue(comments.getText().toString());
                                myref.child("Users").child(patientnum.getText().toString()).child("prescription").child(todaydate)
                                        .child("given by").setValue(docname);

                                Toast.makeText(DoctorPrescription.this,"prescription uploaded",Toast.LENGTH_SHORT)
                                        .show();


                                startActivity(new Intent(DoctorPrescription.this,DoctorHome.class));
                                finish();



                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
}
