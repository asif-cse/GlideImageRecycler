package com.example.retrifitimagerecycler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class AnimeActivity extends AppCompatActivity {

    private String name,description,studio,category,rating,image_url;
    private int episode;
    private TextView tv_name, tv_studio, tv_categorie, tv_description, tv_rating;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anime);
        // hide the default actionbar
        getSupportActionBar().hide();

        name  = getIntent().getExtras().getString("anime_name");
        description = getIntent().getExtras().getString("anime_description");
        studio = getIntent().getExtras().getString("anime_studio") ;
        category = getIntent().getExtras().getString("anime_category");
        episode = getIntent().getExtras().getInt("anime_nb_episode") ;
        rating = getIntent().getExtras().getString("anime_rating") ;
        image_url = getIntent().getExtras().getString("anime_img") ;


        // ini views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);

        tv_name = findViewById(R.id.aa_anime_name);
        tv_studio = findViewById(R.id.aa_studio);
        tv_categorie = findViewById(R.id.aa_categorie) ;
        tv_description = findViewById(R.id.aa_description);
        tv_rating  = findViewById(R.id.aa_rating) ;
        img = findViewById(R.id.aa_thumbnail);

        // setting values to each view

        tv_name.setText(name);
        tv_categorie.setText(category);
        tv_description.setText(description);
        tv_rating.setText(rating);
        tv_studio.setText(studio);

        collapsingToolbarLayout.setTitle(name);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading).error(R.drawable.loading);

        //set image with glide

        Glide.with(this).load(image_url).apply(requestOptions).into(img);
    }
}
