package com.example.roomdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_insert, btnview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );


      //  StudentRepository studentRepository = new StudentRepository ( getApplicationContext () );

        btn_insert = (Button) findViewById ( R.id.btn_insert );
        btnview = (Button) findViewById ( R.id.btn_view );

        btn_insert.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, InsertActivity.class);
                startActivity ( intent );
            }
        } );

        btnview.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (MainActivity.this, com.example.roomdb.View.class );
                startActivity ( intent );
            }
        } );

    }
}