package com.example.android.multi_language.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.android.multi_language.R;
import com.example.android.multi_language.adapter.GalleryAdapter;
import com.example.android.multi_language.helper.NetworkUtils;
import com.example.android.multi_language.helper.OpenImageJsonUtils;
import com.example.android.multi_language.model.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengzhi on 2017/6/22.
 */

public class MainActivity extends AppCompatActivity implements GalleryAdapter.ClickListener {


    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "http://api.androidhive.info/json/glide.json";
    private ArrayList<Image> images;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        images = new ArrayList<>();

        mAdapter = new GalleryAdapter(this, images, this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

        recyclerView.setAdapter(mAdapter);

//        recyclerView.addOnItemTouchListener(new GalleryAdapter.RecyclerTouchListener(getApplicationContext(), recyclerView, new GalleryAdapter.ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("images", images);
//                bundle.putInt("position", position);
//
//                Log.i(TAG, "222" + String.valueOf(images.size()));
//                Log.i(TAG, "ddd" + String.valueOf(position));
//
//                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
//                newFragment.setArguments(bundle);
//                newFragment.show(ft, "slideshow");
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));


        new FetchImagesTask().execute();


    }



    private class FetchImagesTask extends AsyncTask<Void, Void, List<Image>> {
        ProgressDialog dialog;

        public FetchImagesTask() {
            dialog = new ProgressDialog(mContext);

            dialog.setMessage("Downloading json...");

            dialog.show();
        }

        @Override
        protected List<Image> doInBackground(Void... params) {


            try {
                String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(endpoint);
                Log.i(TAG, responseFromHttpUrl);
                List<Image> imagesFromJson = OpenImageJsonUtils.getImagesFromJson(getApplicationContext(), responseFromHttpUrl);
                Log.i(TAG, imagesFromJson.get(0).getSmall());

                Log.i(TAG, imagesFromJson.get(0).getMedium());
                Log.i(TAG, imagesFromJson.get(0).getLarge());

                return imagesFromJson;

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Image> images) {
            dialog.dismiss();
            mAdapter.swapCursor(images);

        }
    }


    @Override
    public void onClick(int position, List<Image> images) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("images", (Serializable) images);
        bundle.putInt("position", position);

        Log.i(TAG, "222" + String.valueOf(images.size()));
        Log.i(TAG, "ddd" + String.valueOf(position));

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        SlideshowDialogFragment newFragment = SlideshowDialogFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, "slideshow");

    }


}
