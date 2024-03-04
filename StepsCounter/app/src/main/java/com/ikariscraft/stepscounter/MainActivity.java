package com.ikariscraft.stepscounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.ikariscraft.stepscounter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        viewModel.getScore().observe(this, score->{
            updateScore(score);
        });

        binding.btnAdd.setOnClickListener(v->{
            viewModel.addPoints(1);
        });

        binding.btnMinus.setOnClickListener(v->{
            viewModel.decreasePoints();
        });
    }

    private void updateScore(int val){
        binding.txtScore.setText(String.valueOf(val));
    }
}