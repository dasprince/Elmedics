package com.example.hp.elmedics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

        final EditText mblnum = (EditText)findViewById(R.id.etpatmblnumdoc);

        final TextView name = (TextView)findViewById(R.id.tvpatnamedoc);
        final TextView email = (TextView)findViewById(R.id.tvpatemaildoc);
        final TextView mobilenumber = (TextView)findViewById(R.id.tvpatmblnumdoc);
        final TextView prescription = (TextView)findViewById(R.id.tvpatpresdoc);

        final Button search = (Button)findViewById(R.id.btnsearchpatdoc);

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        final DatabaseReference myref = firebase.getReference();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mblnum.getText().toString().matches("")){
                    Toast.makeText(ViewPatient.this ,"Please enter patient's mobile number",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.child("Users").hasChild(mblnum.getText().toString())){
                                name.setText(dataSnapshot.child("Users").child(mblnum.getText().toString()).child("name")
                                .getValue().toString());

                                email.setText(dataSnapshot.child("Users").child(mblnum.getText().toString()).child("email")
                                        .getValue().toString());

                                mobilenumber.setText(dataSnapshot.child("Users").child(mblnum.getText().toString()).child("mobile number")
                                        .getValue().toString());

                                prescription.setText("Medicine : " +
                                        dataSnapshot.child("Users").child(mblnum.getText().toString()).child("prescription")
                                        .child("2018-01-10").child("medicines").getValue().toString() + "\n" +"Dozes : "
                                        + dataSnapshot.child("Users").child(mblnum.getText().toString()).child("prescription")
                                        .child("2018-01-10").child("dozes").getValue().toString() +"\n"+"Comments : " +
                                        dataSnapshot.child("Users").child(mblnum.getText().toString()).child("prescription")
                                                .child("2018-01-10").child("comments").getValue().toString());
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
