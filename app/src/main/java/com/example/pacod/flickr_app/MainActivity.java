package com.example.pacod.flickr_app;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
    ListView list;
    String[] web = {
            "Football",
            "Food",
            "Gym",
            "Star wars",
            "Animals",
            "Instagram",

    } ;
    Integer[] imageId = {
            R.drawable.american,
            R.drawable.groceries,
            R.drawable.weight,
            R.drawable.star,
            R.drawable.whale,
            R.drawable.ig


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomAdapter adapter = new
                CustomAdapter(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String  itemValue    = (String) list.getItemAtPosition(position);


                if (isNetworkConnected(getApplicationContext()))
                {
                    Intent intent = new Intent(MainActivity.this,Photo_gallery.class);
                    intent.putExtra("itemValue", itemValue);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(MainActivity.this, "No hay Conexion ", Toast.LENGTH_SHORT).show();
                }



                //Toast.makeText(MainActivity.this, "You Clicked at " + itemValue, Toast.LENGTH_SHORT).show();

            }
        });

    }
    private boolean isNetworkConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context
                .CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info == null || !info.isConnected() || !info.isAvailable()) {
            return false;
        }
        return true;
    }






}