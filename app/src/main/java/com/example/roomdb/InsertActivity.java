package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity {


    EditText RollNo, StudentName, ContactNum, Gender;
    Button Submit;

    String s_rollno, s_studentName, s_contactnum, s_gender = "Male";
    RadioButton rBtn_male, rBtn_female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_insert );


        RollNo = (EditText) findViewById ( R.id.edt_rollno );
        StudentName = (EditText) findViewById ( R.id.edt_studentname );
        ContactNum = (EditText) findViewById ( R.id.edt_contactnumber );
      //  Gender = (EditText) findViewById ( R.id.edt_gender );

        rBtn_male = (RadioButton) findViewById ( R.id.rbtn_male );
        rBtn_female = (RadioButton) findViewById ( R.id.rbtn_female );


        Submit = (Button) findViewById ( R.id.submitbutton );




        Submit.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if(RollNo.getText ().toString ().isEmpty ()
                    || StudentName.getText ().toString ().isEmpty ()
                    || ContactNum.getText ().toString ().isEmpty () ){

                    Toast.makeText ( getApplicationContext (), "Fill Detail", Toast.LENGTH_LONG ).show ();

                }else {
                    s_rollno = RollNo.getText ().toString ().trim ();
                    s_studentName = StudentName.getText ().toString ().trim ();
                    s_contactnum = ContactNum.getText ().toString ().trim ();
                   // s_gender = Gender.getText ().toString ().trim ();

                    //Tost rollno name contactnum gendar

                    if (rBtn_male.isChecked ()){
                        s_gender = "Male";
                    }else if(rBtn_female.isChecked ()){
                        s_gender = "Female";
                    }

                    StudentRepository studentRepository = new StudentRepository ( getApplicationContext () );


                    Student student = new Student ( Integer.parseInt ( s_rollno ), s_studentName, s_contactnum, s_gender );
                    studentRepository.Inserttask ( student );

                    RollNo.setText ( "" );
                    StudentName.setText ( "" );
                    ContactNum.setText ("");
                   // Gender.setText ("");


                }
            }
        } );
    }
}