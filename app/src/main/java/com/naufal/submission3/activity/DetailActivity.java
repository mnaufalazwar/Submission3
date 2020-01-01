package com.naufal.submission3.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.naufal.submission3.R;
import com.naufal.submission3.model.Movie;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvOverview, tvRelease, tvRate;
    ImageView ivPoster;
    public static final String EXTRA_DATA = "extra_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(getString(R.string.movie_desc));
        }

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        tvRelease = findViewById(R.id.tvReleaseDate);
        tvRate = findViewById(R.id.tvRate);
        ivPoster = findViewById(R.id.ivPoster);

        Movie movie = getIntent().getParcelableExtra(EXTRA_DATA);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRelease.setText(movie.getReleaseDate());
        tvRate.setText(""+movie.getRate());
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w185" + movie.getPoster())
                .into(ivPoster);

    }
}
