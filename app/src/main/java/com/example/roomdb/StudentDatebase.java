package com.example.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database ( entities = {Student.class}, version = 1,exportSchema = false)
public abstract class StudentDatebase extends RoomDatabase {

    public abstract StudentDAO studentDAO();
}
