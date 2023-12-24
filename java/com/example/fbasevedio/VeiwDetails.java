package com.example.fbasevedio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class VeiwDetails extends AppCompatActivity {

    TextView  vehicleNumber,engineNumber,chassisNumber,
            ownerName,ownerNIC,ownerTP,ownerAddres;

    Button backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_details);

        vehicleNumber = findViewById(R.id.vehicleNumber);
        engineNumber = findViewById(R.id.engineNumber);
        chassisNumber = findViewById(R.id.chassisNumber);
        ownerName= findViewById(R.id.ownerName);
        ownerNIC = findViewById(R.id.ownerNIC);
        ownerTP = findViewById(R.id.ownerTP);
        ownerAddres = findViewById(R.id.ownerAddress);
        backBtn =findViewById(R.id.backBtn);

        Intent intent= getIntent();
        if (intent != null){
            vehicleNumber.setText(intent.getStringExtra("vehicleNumber"));
            engineNumber.setText(intent.getStringExtra("engineNumber"));
            chassisNumber.setText(intent.getStringExtra("chassisNumber"));
            ownerName.setText(intent.getStringExtra("ownerName"));
            ownerNIC.setText(intent.getStringExtra("ownerNIC"));
            ownerTP.setText(intent.getStringExtra("ownerTP"));
            ownerAddres.setText(intent.getStringExtra("ownerAddress"));

        }

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VeiwDetails.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }
}