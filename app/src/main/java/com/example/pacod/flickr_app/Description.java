package com.example.pacod.flickr_app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Description extends AppCompatActivity {
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);


        ImageView img =(ImageView)findViewById(R.id.imageView);
        TextView txtTitulo=(TextView)findViewById(R.id.titulo);



        //Obtengo el String del intent
        Intent intent = getIntent();
        String urlPhoto = intent.getStringExtra("photo");
        String titulo =  intent.getStringExtra("title");



        txtTitulo.setText(titulo);
        Picasso.with(mContext).load(urlPhoto).into(img);
    }

}
