package com.example.fbasevedio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.google.android.material.navigation.NavigationView;

public class HomePage extends AppCompatActivity {

    Button assign, available;
    FrameLayout vehicle,locked;




    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        vehicle =  findViewById(R.id.vehicleBtn);

        assign = (Button)findViewById(R.id.assignBtn);
        available =(Button)findViewById(R.id.availableBtn);
        locked = findViewById(R.id.lockedBtn);



        vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, MainActivity.class);
                startActivity(intent);

            }
        });

        assign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this, vehicle_assign.class);

                startActivity(intent);


            }
        });

        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomePage.this,AvailableVehicles.class);
                startActivity(intent);
            }
        });

        locked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomePage.this, lockedVehicles.class);
                startActivity(intent);
            }
        });




    }


}