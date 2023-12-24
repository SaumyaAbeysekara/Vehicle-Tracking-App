package com.example.fbasevedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class lockedVehicles extends AppCompatActivity implements UnlockVehicleCallback  {

    private RecyclerView recyclerView;
    AssignmentAdapter adapter;
    private DatabaseReference detailsReference;
    private List<String> availableVehicleList;

    Button locationBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locked_vehicles);


        recyclerView = (RecyclerView) findViewById(R.id.recycleView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FirebaseRecyclerOptions<Assignment> options =
                new FirebaseRecyclerOptions.Builder<Assignment>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Assingment"), Assignment.class)
                        .build();


        adapter = new AssignmentAdapter(options,this);
        recyclerView.setAdapter(adapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    @Override
    public void onUnlockVehicle(String vehicleAssign) {
        Intent intent = new Intent("vehicle_unlocked");
        intent.putExtra("vehicleNumber", vehicleAssign);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
}