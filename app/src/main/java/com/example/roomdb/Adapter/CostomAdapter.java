package com.example.roomdb.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdb.R;
import com.example.roomdb.Student;
import com.example.roomdb.StudentsDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CostomAdapter extends RecyclerView.Adapter<CostomAdapter.MyViewHolder>{

    private ArrayList<Student> database;
    Context context;


    /// myview holder start

    public static  class MyViewHolder extends RecyclerView.ViewHolder{


        TextView tv_rollno;
        TextView tv_studantName;
        TextView tv_contactNum;
        ImageView img_gender;
        ImageView  img_call;
        Button btn_title;


        ConstraintLayout ll_card_student;

        public MyViewHolder(@NonNull View itemView) {
            super ( itemView );

            this.tv_rollno = (TextView) itemView.findViewById ( R.id.tv_rollno );
            this.tv_studantName = (TextView) itemView.findViewById ( R.id.tv_studentName );
            this.tv_contactNum = (TextView) itemView.findViewById ( R.id.tv_contactnum);
            this.img_gender = (ImageView) itemView.findViewById ( R.id.img_gendar );
            this.img_call = (ImageView) itemView.findViewById ( R.id.img_call );
            this.btn_title = (Button) itemView.findViewById ( R.id.btn_title);

            this.ll_card_student = (ConstraintLayout) itemView.findViewById ( R.id.ll_card_student );



        }
    }

    /// myview holder end

    public CostomAdapter(ArrayList<Student> database, Context context){

        this.database = database;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from ( parent.getContext () ).inflate ( R.layout.card_students, parent, false );
        MyViewHolder myViewHolder = new MyViewHolder ( view );

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        TextView tv_rollno = holder.tv_rollno;
        TextView tv_studentName = holder.tv_studantName;
        TextView tv_contactNum = holder.tv_contactNum;
        ImageView img_gender = holder.img_gender;
        ImageView img_call = holder.img_call;
        Button btn_title = holder.btn_title;

        ConstraintLayout ll_card_student = holder.ll_card_student;

        tv_rollno.setText ( database.get ( position ).rollno + "" );
        tv_studentName.setText ( database.get ( position ).student_name + "" );
        tv_contactNum.setText ( database.get ( position ).contanctno+ "" );

        if (database.get ( position ).gender.equalsIgnoreCase ( "male" )){
            img_gender.setImageResource ( R.drawable.coner );
        }else  if(database.get ( position ).gender.equalsIgnoreCase ( "female" )){

            img_gender.setImageResource ( R.drawable.femal);
        }

// logic to set title
        btn_title.setText ( database.get ( position ).student_name.toUpperCase ().charAt ( 0 ) + " " );

 //random color
        Random random = new Random();
        int red = random.nextInt (255);
        int green = random.nextInt (255);
        int blue = random.nextInt (255);
        btn_title.setBackgroundColor ( Color.rgb ( red, green, blue ) );


        //call
        if (database.get ( position ).contanctno.length () > 9) {

            img_call.setVisibility ( View.VISIBLE );

            img_call.setOnClickListener ( new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent ( Intent.ACTION_DIAL, Uri.parse ( "tel:" + database.get ( position ).contanctno ) );
                    context.startActivity ( intent );
                }
            } );

        }else {
            img_call.setVisibility ( View.GONE );
        }


        //update student start 2nd part

        ll_card_student.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                int rollno = database.get ( position ).rollno;
                 String student_name = database.get ( position ).student_name;
                 String contactno = database.get ( position ).contanctno;
                 String gender = database.get ( position ).gender;

               // Toast.makeText ( context, rollno + "\n" + student_name+ "\n" + contactno+ "\n" + gender,Toast.LENGTH_LONG).show ();

                Intent intent = new Intent (context, StudentsDetails.class);

                intent.putExtra ( "rollno",rollno);
                intent.putExtra ( "student_name",student_name );
                intent.putExtra ( "contactno", contactno );
                intent.putExtra ( "gender", gender );
                context.startActivity ( intent );




            }
        } );
        // update student end
    }

    @Override
    public int getItemCount() {
        return database.size ();
    }
}
