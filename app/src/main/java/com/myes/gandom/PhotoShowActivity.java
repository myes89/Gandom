package com.myes.gandom;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import static com.myes.gandom.MainActivity.PHOTO_EXTRA;

public class PhotoShowActivity extends AppCompatActivity {

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_show);

        ImageView imageView = (ImageView) findViewById(R.id.image_show);

        Intent intent = getIntent();
        url = intent.getStringExtra(PHOTO_EXTRA);
        Glide.with(this)
                .load("http://gandom.co/devTest/1/images/" + url)
                .into(imageView);
    }
}
