package com.ikariscraft.earthquakes.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ikariscraft.earthquakes.R;
import com.ikariscraft.earthquakes.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        String place = extras.getString("PLACE");
        String id = extras.getString("ID");
        double magnitude = extras.getDouble("MAGNITUDE");

        binding.magnitudeText.setText(magnitude + " ");
        binding.placeText.setText(place);
        binding.idText.setText(id);
    }
}