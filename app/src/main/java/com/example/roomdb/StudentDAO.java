package com.example.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StudentDAO {

    @Insert
    Long insertTask(Student student);

    @Update
    void updatetask(Student student);

    @Delete
    void deletetask(Student student);



    // adapter pre kaj
    @Query ( "select * from student order by rollno asc" )
    List<Student> getAll();
}
