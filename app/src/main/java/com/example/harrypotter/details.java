package com.example.harrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recibirdato();
    }


    public void recibirdato(){
        Bundle extras=getIntent().getExtras();
        String nom= extras.getString("dato1");
        String link= extras.getString("dato2");
        String des= extras.getString("dato3");
        String mo= extras.getString("dato4");
        int id= extras.getInt("dato5");
        ImageView img= findViewById(R.id.imageView2);
        TextView txt=findViewById(R.id.textView2);
        TextView txt2=findViewById(R.id.textView3);
        TextView txt3=findViewById(R.id.textid);
        TextView txt4=findViewById(R.id.textmov);


        Picasso.get()
                .load(link)
                .into(img);
        txt.setText(nom);
        txt2.setText(des);
        txt3.setText("ID: "+id);
        txt4.setText("MOVEMENT: "+mo);


    }
}