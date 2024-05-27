package com.mirea.kt.practic2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class MainActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etSpecialty;
    private Switch switchCertified;
    private Button btnSave, btnViewDoctors;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);
        etSpecialty = findViewById(R.id.etSpecialty);
        switchCertified = findViewById(R.id.switchCertified);
        btnSave = findViewById(R.id.btnSave);
        btnViewDoctors = findViewById(R.id.btnViewDoctors);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "doctor-database")
                .allowMainThreadQueries()
                .build();

        btnSave.setOnClickListener(v -> {
            try {
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String specialty = etSpecialty.getText().toString();
                boolean certified = switchCertified.isChecked();

                Doctor doctor = new Doctor(firstName, lastName, specialty, certified);
                db.doctorDao().insert(doctor);
                Toast.makeText(this, "Doctor Saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e("MainActivity", "Error saving doctor", e);
                Toast.makeText(this, "Error saving doctor", Toast.LENGTH_SHORT).show();
            }
        });

        btnViewDoctors.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, DoctorListActivity.class);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("MainActivity", "Error starting DoctorListActivity", e);
                Toast.makeText(this, "Error viewing doctors", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

