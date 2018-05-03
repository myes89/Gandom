package com.myes.gandom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.onecode.stickyheadergrid.tonicartos.StickyGridHeadersGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String PHOTO_EXTRA = "com.myes.gandom.photoShow";
    protected PhotoAdapter mPhotoAdapter;
    private StickyGridHeadersGridView mGridView;

    private List<Model> photoModel;
    Model[] mModels;
    PhotoModel [] mPhotoModels;
    List<PhotoModel> mPhotoList = new ArrayList<>();

    private void setUI() {
        mGridView = findViewById(R.id.gridview);
    }

    private void init() {
        mPhotoAdapter = new PhotoAdapter(this);
        mGridView.setAdapter(mPhotoAdapter);
    }

    private  void populate() {

        mModels = new Model[photoModel.size()];
        mModels = photoModel.toArray(mModels);

        for (Model mModel : mModels) {

            mPhotoModels = new PhotoModel[mModel.getPhotoList().size()];

            for (int i = 0; i < mModel.getPhotoList().size(); i++) {

                mPhotoModels[i] = new PhotoModel(mModel.getDescription(), mModel.getPhotoList().get(i));
                mPhotoList.add(mPhotoModels[i]);
            }
        }

        mPhotoAdapter.appendBottomAll(mPhotoList);
    }

    String gandomUrl = "http://gandom.co/devTest/1/home";

    Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, gandomUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray listArray = response.getJSONArray("list");
                            photoModel = mGson.fromJson(listArray.toString(),
                                    new TypeToken<List<Model>>() {
                                    }.getType());

                            populate();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        VolleySingleton.getInstance(this).addToRequestQueue(jsObjRequest);

        setUI();
        init();

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this , PhotoShowActivity.class);
                intent.putExtra(PHOTO_EXTRA , mPhotoList.get(position).getUrl());
                startActivity(intent);
            }
        });
    }
}
