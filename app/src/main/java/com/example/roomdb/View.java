package com.example.roomdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.example.roomdb.Adapter.CostomAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class View extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    ArrayList<Student> studentArrayList, studentArrayList_search;


    EditText edt_search;

    CostomAdapter costomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_view );


        recyclerView = (RecyclerView) findViewById ( R.id.recyclerView );
        recyclerView.setHasFixedSize ( true );

        layoutManager = new LinearLayoutManager ( this );
        recyclerView.setLayoutManager ( layoutManager );
        recyclerView.setItemAnimator ( new DefaultItemAnimator () );

        edt_search = (EditText) findViewById ( R.id.edt_search );

        //search method 2nd part start

        edt_search.addTextChangedListener ( new TextWatcher () {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String text = s.toString ();
                Filter ( text );

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
//search method 2nd part ends

       new LoadDataTask ().execute (  );
    }

    class LoadDataTask extends AsyncTask<Void, Void, Void>{

        StudentRepository studentRepository;
        List<Student>studentList;

        @Override
        protected void onPreExecute() {
            super.onPreExecute ();
            studentRepository = new StudentRepository ( getApplicationContext () );
        }

        @Override
        protected Void doInBackground(Void... voids) {

            studentList = studentRepository.getStudents ();
            studentArrayList = new ArrayList<> ();
            studentArrayList_search = new ArrayList<> ();

            for(int i = 0; i < studentList.size (); i++){
                studentArrayList.add ( studentList.get ( i ));
                studentArrayList_search.add ( studentArrayList.get ( i ) );

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute ( unused );

          costomAdapter = new CostomAdapter ( studentArrayList,View.this);
            recyclerView.setAdapter ( costomAdapter );
        }
    }
    // scarch 1st part
    // filter method starts scarch

    public  void Filter(String charText){

        charText = charText.toLowerCase( Locale.getDefault () );
        Log.d ( "filter", charText + "" );

        studentArrayList.clear ();

        if(charText.length () == 0){

            studentArrayList.addAll ( studentArrayList_search );
            Log.d ( "load data", "All" );
        }else {

            Log.d ( "load data", "Fitered" );
            for (Student student : studentArrayList_search) {
                if (student.student_name.toLowerCase ( Locale.getDefault () ).contains ( charText )

                        || String.valueOf (student.rollno).toLowerCase ( Locale.getDefault () ).contains ( charText )) {

                    studentArrayList.add ( student );
                }
            }
        }

            costomAdapter.notifyDataSetChanged ();


    }
    // filter method ends scarch


    //update restart


    @Override
    protected void onRestart() {
        super.onRestart ();

        new LoadDataTask ().execute (  );
    }
}