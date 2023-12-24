package com.example.fbasevedio;

import static com.example.fbasevedio.R.id.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        final EditText user =findViewById(reg_username);
        final EditText email =  findViewById(R.id.email);
        final EditText pwd =findViewById(R.id.reg_password);
        final EditText con_pwd =  findViewById(R.id.confirm_password);

        final  Button createBtn = findViewById(R.id.btn3);







        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // get data from text editor
                final String userTxt = user.getText().toString();
                final String emailTxt = email.getText().toString();
                final String pwdTxt =  pwd.getText().toString();
                final String con_pwdTxt = con_pwd.getText().toString();

                if (userTxt.isEmpty() || emailTxt.isEmpty() || pwdTxt.isEmpty() || con_pwdTxt.isEmpty()) {
                    Toast.makeText(Account.this,"Please fill all fields.",Toast.LENGTH_SHORT).show();


                }
                else if(!pwdTxt.equals(con_pwdTxt)){
                    Toast.makeText(Account.this,"Passwords are not matching ",Toast.LENGTH_SHORT).show();

                }
                else{
                    databaseReference.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(userTxt)){
                                Toast.makeText(Account.this,"Username is already registered",Toast.LENGTH_SHORT).show();

                            }
                            else{
                                databaseReference.child("users").child(userTxt).child("user").setValue(userTxt);
                                databaseReference.child("users").child(userTxt).child("email").setValue(emailTxt);
                                databaseReference.child("users").child(userTxt).child("pwd").setValue(pwdTxt);

                                Toast.makeText(Account.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                                finish();


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });




                }

            }
        });
    }
}