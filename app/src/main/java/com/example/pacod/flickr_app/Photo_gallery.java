package com.example.pacod.flickr_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import static android.R.attr.id;

public class Photo_gallery extends AppCompatActivity {
    String api_key = "f9e5a18f1e05e3acf33ff4b007d5761e";
    String restURL;
    Connection con = new Connection();

    ArrayList<String> id_photo= new ArrayList<>();
    ArrayList<String> url= new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        //Obtengo el String del intent
        Intent intent = getIntent();
        String idClasificacion = intent.getStringExtra("itemValue");


        /*Toast.makeText(Photo_gallery.this, "" + idClasificacion,
                Toast.LENGTH_SHORT).show();*/

        restURL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key="+ api_key +"&tags="+ idClasificacion +"&format=json&nojsoncallback=1";


        new AsyncTask<String,Void,Void>() {
            String data="";





            @Override
            protected Void doInBackground(String... params) {

                try {
                    data = con.run(restURL);
                    Log.d("Data:",data);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                super.onPostExecute(result);

                JSONTokenizer jsonTokenizer = new JSONTokenizer(data);
                try {
                    JSONArray jsonArray = jsonTokenizer.getArrayPhotos();
                    Log.d("array: ", jsonArray.toString());


                    for (int i = 0; i < 8; i++)
                    {
                        JSONObject jsonObject = jsonArray.optJSONObject(i);
                        String id = jsonObject.getString("id");
                        String farm = jsonObject.getString("farm");
                        String server = jsonObject.getString("server");
                        String secret = jsonObject.getString("secret");


                        String photo ="https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+".jpg";

                        id_photo.add(id);
                        url.add(photo);
                        Log.d("url",photo);

                    }

                    GridView gridview = (GridView) findViewById(R.id.gridview);
                    gridview.setAdapter(new ImageAdapter(getBaseContext(),url));

                    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView<?> parent, View v,
                                                int position, long id) {
                            Toast.makeText(Photo_gallery.this, "" + position,
                                    Toast.LENGTH_SHORT).show();
                        }
                    });











                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }.execute();


    }


}
