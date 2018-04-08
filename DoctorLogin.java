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

public class DoctorLogin extends AppCompatActivity {
    public static String docname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        final EditText mobilenum = (EditText)findViewById(R.id.etmobilenumlogin);
        final EditText password = (EditText)findViewById(R.id.etpasswordlogin);
        Button login = (Button)findViewById(R.id.btnlogindoc);

        //firebase database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference myref = firebaseDatabase.getReference();

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                if(mobilenum.getText().toString().matches("" )|| password.getText().toString().matches("")){
                    Log.i("info:","details are empty");
                    Toast.makeText(DoctorLogin.this,"Mobile number/password is required",Toast.LENGTH_SHORT).show();
                }
                else{
                    Log.i("info:","details entered");
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Doctors").hasChild(mobilenum.getText().toString())){
                                if(dataSnapshot.child("Doctors").child(mobilenum.getText().toString()).child("password").getValue()
                                        .toString().matches(password.getText().toString())){
                                    //correct details
                                    Log.i("success","database reached");
                                    docname = dataSnapshot.child("Doctors").child(mobilenum.getText().toString())
                                            .child("name").getValue().toString();
                                    Toast.makeText(DoctorLogin.this, "Login successful",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DoctorLogin.this , DoctorHome.class));
                                }
                                else{
                                    Log.i("fail","password incorrect");
                                    Toast.makeText(DoctorLogin.this, "Incorrect password entered",Toast.LENGTH_SHORT)
                                            .show();
                                    password.setText("");
                                }
                            }
                            else{
                                Log.i("fail:","user not found");
                                Toast.makeText(DoctorLogin.this, "User not found",Toast.LENGTH_SHORT).show();
                                mobilenum.setText("");
                                password.setText("");
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
