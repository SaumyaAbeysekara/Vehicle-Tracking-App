package com.example.fbasevedio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class DriverView extends AppCompatActivity {

    RecyclerView recyclerView3;
    SecondAdapter secondAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_view);

        recyclerView3 =(RecyclerView)findViewById(R.id.recycleViewDriver);
        recyclerView3.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<SecondModel> options =
                new FirebaseRecyclerOptions.Builder<SecondModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("driver"), SecondModel.class)
                        .build();


        secondAdapter = new SecondAdapter(options);
        recyclerView3.setAdapter(secondAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        secondAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        secondAdapter.stopListening();
    }


}