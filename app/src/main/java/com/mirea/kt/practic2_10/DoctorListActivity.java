package com.mirea.kt.practic2_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class DoctorListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DoctorAdapter doctorAdapter;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "doctor-database")
                .allowMainThreadQueries()
                .build();
        List<Doctor> doctorList = db.doctorDao().getAllDoctors();

        doctorAdapter = new DoctorAdapter(doctorList);
        recyclerView.setAdapter(doctorAdapter);

        doctorAdapter.setOnItemClickListener(new DoctorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Doctor doctor) {

            }

            @Override
            public void onItemLongClick(Doctor doctor) {
                // Удаление доктора при долгом нажатии
                db.doctorDao().delete(doctor);
                doctorAdapter.removeDoctor(doctor);
                Toast.makeText(DoctorListActivity.this, "Doctor Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        // Кнопка для выхода в MainActivity
        Button btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(DoctorListActivity.this, MainActivity.class);
            startActivity(intent);
            finish();  // Закрыть текущую активность
        });
    }
}

