package com.mirea.kt.practic2_10;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "doctors")
public class Doctor {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "first_name")
    public String firstName;
    @ColumnInfo(name = "last_name")
    public String lastName;
    @ColumnInfo(name = "specialty")
    public String specialty;
    @ColumnInfo(name = "certified")
    public boolean certified;

    public Doctor(String firstName, String lastName, String specialty, boolean certified) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialty = specialty;
        this.certified = certified;
    }
}

