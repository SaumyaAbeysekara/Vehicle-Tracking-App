package com.example.fbasevedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AvailableVehicles extends AppCompatActivity {
    private DatabaseReference detailsReference;
    private DatabaseReference assignmentReference;
    private ListView availableVehiclesListView;
    private List<String> availableVehicleList;


    private BroadcastReceiver unlockReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("vehicle_unlocked".equals(intent.getAction())) {
                // Refresh the available vehicles list
                loadAvailableVehicles();
            }
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(unlockReceiver, new IntentFilter("vehicle_deleted"));

    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(unlockReceiver);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_vehicles);

        detailsReference = FirebaseDatabase.getInstance().getReference().child("Details");
        assignmentReference = FirebaseDatabase.getInstance().getReference().child("Assignment");

        availableVehiclesListView = findViewById(R.id.availableVehiclesListView);
        availableVehicleList = new ArrayList<>();
        loadAvailableVehicles();


    }

    private void loadAvailableVehicles() {
        detailsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                availableVehicleList.clear();

                for (DataSnapshot detailsSnapshot : snapshot.getChildren()) {
                    String vehicleNumber = detailsSnapshot.getKey();

                    if (!isVehicleAssigned(vehicleNumber)) {
                        availableVehicleList.add("Vehicle Number: " +vehicleNumber);
                    }else{
                        Toast.makeText(AvailableVehicles.this, "Vehicle " + vehicleNumber + " is already assigned.", Toast.LENGTH_SHORT).show();


                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(AvailableVehicles.this,
                        android.R.layout.simple_list_item_1, availableVehicleList);
                availableVehiclesListView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("AvailableVehicles", "Error loading available vehicles:" + error.getMessage());

            }
        });
    }

    private boolean isVehicleAssigned(String vehicleAssign) {
        final boolean[] isAssigned={false};
        assignmentReference.child(vehicleAssign).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                isAssigned[0] = snapshot.exists();

                Log.d("VehicleAssignment", "Vehicle " + vehicleAssign + " assigned: " + isAssigned[0]);

                if (isAssigned[0]) {
                    Toast.makeText(AvailableVehicles.this, "Error: Vehicle " + vehicleAssign + " is already assigned.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("VehicleAssignment", "Error checking assignment: " + error.getMessage());




            }
        });

        return isAssigned[0];
    }

    public void onAssignmentComplete(){
        loadAvailableVehicles();
    }



   }