package com.example.fbasevedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class vehicle_assign extends AppCompatActivity {

    private EditText vehicleAssignment, driverAssignment,driverAssignmentTP;
    private Button assignBtn;
    private DatabaseReference databaseReference;
    private static AssignmentCallback callBack;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_assign);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        vehicleAssignment = findViewById(R.id.vehicleAssigned);
        driverAssignment = findViewById(R.id.driverAssigned);
        driverAssignmentTP = findViewById(R.id.driverAssignedNo);


        assignBtn = findViewById(R.id.assignBtn);

        assignBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assignDataToFirebase();

            }
        });

    }
    private void assignDataToFirebase(){
        String vehicleAssign = vehicleAssignment.getText().toString().trim();
        String driverAssign = driverAssignment.getText().toString().trim();
        String driverAssignNo = driverAssignmentTP.getText().toString().trim();

        if (!vehicleAssign.isEmpty() && !driverAssign.isEmpty() && !driverAssign.isEmpty()) {
            databaseReference.child("Assingment").child(vehicleAssign).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (!snapshot.exists()) {

                        Assignment assignment = new Assignment(vehicleAssign, driverAssign,driverAssignNo);

                        databaseReference.child("Assingment").child(vehicleAssign).setValue(assignment);
                        databaseReference.child("Details").child(vehicleAssign).removeValue();

                        vehicleAssignment.setText("");
                        driverAssignment.setText("");
                        driverAssignmentTP.setText("");


                        Toast.makeText(vehicle_assign.this, "Assigned", Toast.LENGTH_SHORT).show();

                        if (callBack != null) {
                            callBack.onAssignmentComplete();
                        }
                    }else {
                            Toast.makeText(vehicle_assign.this, "" +
                                    " Already Assigned", Toast.LENGTH_SHORT).show();

                        }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("vehicle_assign", "Database error" + error.getMessage());

                }
            });
        } else{
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show();
        }
    }
    public static void setCallBack(AssignmentCallback callback) {

            vehicle_assign.callBack = callback;
    }


}