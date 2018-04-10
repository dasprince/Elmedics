package com.example.hp.elmedics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewPatient extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient);

        //final SearchView svpat = (SearchView)findViewById(R.id.svpatient);
        final EditText searchpat = (EditText)findViewById(R.id.etmblnumpatdoc);

        final TextView name = (TextView)findViewById(R.id.tvpatnamedoc);
        final TextView email = (TextView)findViewById(R.id.tvemailpatdoc);
        final TextView mobile = (TextView)findViewById(R.id.tvmblnumpatdoc);

        Button search = (Button)findViewById(R.id.btnsearchdetails);

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        final DatabaseReference myref = firebase.getReference();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchpat.getText().toString().matches("")){
                    Toast.makeText(ViewPatient.this,"Please enter mobile number",Toast.LENGTH_SHORT)
                            .show();
                }
                else{
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Users").hasChild(searchpat.getText().toString())){
                                name.setText(dataSnapshot.child("Users").child(searchpat.getText().toString()).child("name").getValue()
                                .toString());

                                email.setText(dataSnapshot.child("Users").child(searchpat.getText().toString()).child("email").getValue()
                                        .toString());

                                mobile.setText(dataSnapshot.child("Users").child(searchpat.getText().toString()).child("mobile number").getValue()
                                        .toString());
                            }
                            else{
                                Toast.makeText(ViewPatient.this,"User not found",Toast.LENGTH_SHORT).show();
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
