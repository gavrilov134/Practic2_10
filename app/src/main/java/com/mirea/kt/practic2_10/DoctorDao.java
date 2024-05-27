package com.mirea.kt.practic2_10;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DoctorDao {
    @Insert
    void insert(Doctor doctor);

    @Query("SELECT * FROM doctors")
    List<Doctor> getAllDoctors();

    @Delete
    void delete(Doctor doctor);
}


