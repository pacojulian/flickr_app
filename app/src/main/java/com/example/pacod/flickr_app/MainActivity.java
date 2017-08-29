package com.example.pacod.flickr_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class MainActivity extends Activity {
    ListView list;
    String[] web = {
            "Instragam",
            "Twitter",
            "Facebook",
            "Microsoft",
            "Apple",
            "Windows",

    } ;
    Integer[] imageId = {
            R.drawable.ig,
            R.drawable.ig,
            R.drawable.ig,
            R.drawable.ig,
            R.drawable.ig,
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


                Intent intent = new Intent(MainActivity.this,Photo_gallery.class);
                intent.putExtra("itemValue", itemValue);
                startActivity(intent);

                //Toast.makeText(MainActivity.this, "You Clicked at " + itemValue, Toast.LENGTH_SHORT).show();

            }
        });

    }

}