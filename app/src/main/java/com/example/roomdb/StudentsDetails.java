package com.example.roomdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class StudentsDetails extends AppCompatActivity {

    EditText edt_rollno, edt_student_name, edt_contactno;
    Button btn_update, btn_delete;
     int s_rollno;
     String s_student_name="", s_contactno="", s_gender="";
     RadioButton r_btn_male, r_btn_female;


     String srollno_to_update="",sstudent_name_to_update="", scontact_to_update="", sgender_to_update="";
     String srollno_to_delete="",sstudent_name_to_delete="", scontact_to_delete="", sgender_to_delete="";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_students_details );


        // find view by ids starts 1st part

        edt_rollno = (EditText) findViewById ( R.id.edt_rollno );
        edt_student_name = (EditText) findViewById ( R.id.edt_studentname );
        edt_contactno = (EditText) findViewById ( R.id.edt_contactnumber );
        r_btn_male = (RadioButton) findViewById ( R.id.rbtn_male );
        r_btn_female = (RadioButton) findViewById ( R.id.rbtn_female );
        btn_update = (Button) findViewById ( R.id.updatebutton );
        btn_delete = (Button) findViewById ( R.id.deletebutton );
        // find view by ids ends


        //get value from custom adapter start 2nd part
         Bundle data = getIntent ().getExtras ();

         if(data != null){

             s_rollno = data.getInt ( "rollno" );
             s_student_name = data.getString ( "student_name" );
             s_contactno = data.getString ( "contactno" );
             s_gender = data.getString ( "gender" );

         }

        // get value  from cusrtm adaprter ends

        // set value from Ui start 3rd part button
         edt_rollno.setText ( s_rollno+ "" );
         edt_student_name.setText ( s_student_name+"" );
         edt_contactno.setText ( s_contactno+ "" );


         if(s_gender.trim ().toLowerCase ().equalsIgnoreCase ( "male" )){
             r_btn_male.setChecked ( true );
         }else  if(s_gender.trim ().toLowerCase ().equalsIgnoreCase ( "female" )){
             r_btn_female.setChecked ( true );

         }
        // set value from UI ends


        //student update start 4th part
        btn_update.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                if(edt_student_name.getText ().toString ().isEmpty () ||

                    edt_contactno.getText ().toString ().isEmpty () ){

                    Toast.makeText ( getApplicationContext (), "Fill detail", Toast.LENGTH_LONG ).show ();

                    }else {

                    srollno_to_update = edt_rollno.getText ().toString ().trim ();
                    sstudent_name_to_update = edt_student_name.getText ().toString ().trim ();
                    scontact_to_update = edt_contactno.getText ().toString ().trim ();

                    if (r_btn_male.isChecked ()){

                        sgender_to_update = "Male";
                    }else  if(r_btn_female.isChecked ()){
                        sgender_to_update = "Female";
                    }

                    StudentRepository studentRepository = new StudentRepository ( getApplicationContext () );

                    Student student = new Student ( Integer.parseInt ( srollno_to_update ),sstudent_name_to_update, scontact_to_update, sgender_to_update );

                    studentRepository.UpdateTask ( student );
                    Toast.makeText ( getApplicationContext (), "Value Update.." , Toast.LENGTH_LONG).show ();

                    finish ();



                }
                }

        } );
         // studant update ends


        //delete 2nd part
        btn_delete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                srollno_to_delete = edt_rollno.getText ().toString ().trim ();
                sstudent_name_to_delete = edt_student_name.getText ().toString ().trim ();
                scontact_to_delete = edt_contactno.toString ().trim ();

                if (r_btn_male.isChecked ()){
                    sgender_to_delete = "Male";
                }else if(r_btn_female.isChecked ()){
                    sgender_to_delete = "Female";
                }
                Student student_to_delete = new Student ( Integer.parseInt ( srollno_to_delete ),sstudent_name_to_delete, scontact_to_delete,sgender_to_delete );
                generate_delete_dialog ( student_to_delete );

            }
        } );

    }

    //generate alert dialog for delete start 1st part


    public void generate_delete_dialog(Student student){

        final Student student_about_to_delete = student;

        AlertDialog.Builder builder = new AlertDialog.Builder ( StudentsDetails.this,R.style.AppCompaAlertDialogStyle );
        builder.setTitle ( "WARNING" );
        builder.setMessage ( "Are you sure to delete ?\n" + "Rollno = "+ student_about_to_delete.rollno+"\n" + "Name = "+student_about_to_delete.student_name );
        builder.setIcon ( R.drawable.close_24 );

        builder.setPositiveButton ( "Delete", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //write delete code over here
                StudentRepository studentRepository = new StudentRepository ( getApplicationContext () );
                studentRepository.DelateTask ( student_about_to_delete );
                Toast.makeText ( StudentsDetails.this, "values Deletes!", Toast.LENGTH_LONG ).show ();
                finish ();
            }
        } );

        builder.setNegativeButton ( "cancel", new DialogInterface.OnClickListener () {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        } );

        AlertDialog deleteDialog = builder.create ();
        deleteDialog.show ();

    }
    //generate alert dialog for delete ends
}