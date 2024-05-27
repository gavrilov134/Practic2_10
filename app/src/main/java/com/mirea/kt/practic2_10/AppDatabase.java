package com.mirea.kt.practic2_10;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Doctor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DoctorDao doctorDao();
}
