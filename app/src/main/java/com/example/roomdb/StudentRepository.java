package com.example.roomdb;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.room.Room;

import java.util.List;

public class StudentRepository {

    private String DB_Name = "studentdb";

    private  StudentDatebase studentDatebase;
    Context context;

    public StudentRepository(Context context){

        this.context = context;
        studentDatebase = Room.databaseBuilder (context, StudentDatebase.class, DB_Name).build ();

      //  Toast.makeText ( context, "Database created....", Toast.LENGTH_LONG ).show ();
    }

    //insertTask starts

    public void Inserttask(final Student student){

        new AsyncTask<Void, Void, Void> (){

            @Override
            protected Void doInBackground(Void... voids) {
                studentDatebase.studentDAO ().insertTask ( student );

                return null;
            }

            @Override
            protected void onPostExecute(Void avoid) {
                super.onPostExecute ( avoid);

                Toast.makeText ( context, student.student_name+ " is Inserted ", Toast.LENGTH_LONG ).show ();

            }
        }.execute (  );
    }

    //insert task ends

    //get data task starts======

    public List<Student> getStudents(){

        List<Student> studentList = studentDatebase.studentDAO ().getAll ();
        return studentList;
    }
    //get data task ends ======



    // update option student start 1st part

    public  void UpdateTask(final  Student  student){

        new AsyncTask<Void, Void, Void> (){

            @Override
            protected Void doInBackground(Void... voids) {
                studentDatebase.studentDAO ().updatetask ( student );
                return null;
            }
        }.execute (  );

    }
    // update option student ends


    // Delate option student start 1st part

    public  void DelateTask(final  Student  student){

        new AsyncTask<Void, Void, Void> (){

            @Override
            protected Void doInBackground(Void... voids) {
                studentDatebase.studentDAO ().deletetask ( student );
                return null;
            }
        }.execute (  );

    }
    // Delate option student ends
}
