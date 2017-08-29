package com.example.pacod.flickr_app;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pacod on 29/08/2017.
 */

public class JSONTokenizer {

    String data;
    public JSONTokenizer(String data) {
        this.data = data;
    }

    public JSONArray getArrayPhotos() throws JSONException {
        JSONObject jsonObject = new JSONObject(data);
        String photo = jsonObject.getString("photos");
        JSONObject photoObj = new JSONObject(photo);
        String photoString = photoObj.getString("photo");
        JSONArray jsonArray = new JSONArray(photoString);

        return  jsonArray;
    }
}
