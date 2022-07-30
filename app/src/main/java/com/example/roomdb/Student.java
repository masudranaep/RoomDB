package com.example.roomdb;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Student {

    @PrimaryKey
    public int rollno;

    @ColumnInfo(name = "student_name")
    public String student_name;

    @ColumnInfo(name = "contactno")
    public String contanctno;

    @ColumnInfo(name = "gender")
    public String gender;

    public Student(int rollno, String student_name, String contanctno, String gender) {
        this.rollno = rollno;
        this.student_name = student_name;
        this.contanctno = contanctno;
        this.gender = gender;
    }
}
