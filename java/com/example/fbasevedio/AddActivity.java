package com.example.fbasevedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText vehicleNumber,engineNumber,chassisNumber,ownerName,ownerNIC,ownerTP,ownerAddress;
    Button addBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        vehicleNumber=(EditText)findViewById(R.id.vehicleNumber);
        engineNumber=(EditText)findViewById(R.id.engineNumber);
        chassisNumber=(EditText)findViewById(R.id.chassisNumber);
        ownerName=(EditText)findViewById(R.id.ownerName);
        ownerAddress=(EditText)findViewById(R.id.ownerAddress);
        ownerNIC=(EditText)findViewById(R.id.ownerNIC);
        ownerTP = (EditText)findViewById(R.id.ownerTP);

        addBtn = (Button)findViewById(R.id.saveBtn);
        backBtn =(Button)findViewById(R.id.backBtn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
    private void insertData(){
        String vehicleNumberText = vehicleNumber.getText().toString();

        if (vehicleNumberText.isEmpty() || ownerName.getText().toString().isEmpty() ||
                engineNumber.getText().toString().isEmpty() || chassisNumber.getText().toString().isEmpty() ||
                ownerNIC.getText().toString().isEmpty() || ownerAddress.getText().toString().isEmpty() ||
                ownerTP.getText().toString().isEmpty()) {
            Toast.makeText(AddActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        Map<String,Object> map = new HashMap<>();
        map.put("vehicleNumber",vehicleNumber.getText().toString());
        map.put("ownerName",ownerName.getText().toString());
        map.put("engineNumber",engineNumber.getText().toString());
        map.put("chassisNumber",chassisNumber.getText().toString());
        map.put("ownerNIC",ownerNIC.getText().toString());
        map.put("ownerAddress",ownerAddress.getText().toString());
        map.put("ownerTP",ownerTP.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("Details").child(vehicleNumberText)
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
                        
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(AddActivity.this, "Error", Toast.LENGTH_SHORT).show();


                    }
                });

    }
}