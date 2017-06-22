package com.example.android.multi_language.helper;

import android.content.ContentValues;
import android.content.Context;

import com.example.android.multi_language.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengzhi on 2017/6/22.
 */

public class OpenImageJsonUtils {

    public static List<Image> getImagesFromJson(Context context, String imageJsonStr)
            throws JSONException {

        List<Image> images = new ArrayList<>();



        JSONArray imageArray = new JSONArray(imageJsonStr);

        for (int i = 0; i < imageArray.length(); i++) {

            JSONObject object = imageArray.getJSONObject(i);
            Image image = new Image();
            image.setName(object.getString("name"));

            JSONObject url = object.getJSONObject("url");
            image.setSmall(url.getString("small"));
            image.setMedium(url.getString("medium"));
            image.setLarge(url.getString("large"));
            image.setTimestamp(object.getString("timestamp"));

            images.add(image);
        }



        return images;
    }
}
