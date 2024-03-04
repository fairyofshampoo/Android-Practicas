package com.ikariscraft.basketballcount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ikariscraft.basketballcount.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding.btnNext.setOnClickListener(v->{
            try{
                int local_team_score = Integer.parseInt(binding.txtLocalScore.getText().toString());
                int visitant_team_score = Integer.parseInt(binding.txtVisitantScore.getText().toString());
                openIntent(local_team_score, visitant_team_score);
            } catch(NumberFormatException ex){
                Log.e("MainActivity", ex.toString());

                Toast.makeText(this, "Please add a numeric value in the fields", Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.getLocalScore().observe(this, localScore->{
            updateLocalScore(localScore);
        });

        viewModel.getVisitantScore().observe(this, visitantScore->{
            updateVisitantScore(visitantScore);
        });

        binding.btnPlusOneLocal.setOnClickListener(v->{
            viewModel.addLocalPoints(1);
        });

        binding.btnPlusTwoLocal.setOnClickListener(v->{
            viewModel.addLocalPoints(2);
        });

        binding.btnMinusLocal.setOnClickListener(v->{
            viewModel.decreaseLocalPoints();
        });

        binding.btnPlusOneVisitant.setOnClickListener(v->{
            viewModel.addVisitantPoints(1);
        });

        binding.btnPlusTwoVisitant.setOnClickListener(v->{
            viewModel.addVisitantPoints(2);
        });

        binding.btnMinusVisitant.setOnClickListener(v->{
            viewModel.decreaseVisitantPoints();
        });

        binding.btnRefresh.setOnClickListener(v->{
            viewModel.resetScore();
        });
    }

    private void updateLocalScore(int val){
        binding.txtLocalScore.setText(String.valueOf(val));
    }
    private void updateVisitantScore(int val){
        binding.txtVisitantScore.setText(String.valueOf(val));
    }

    private void openIntent(int local, int visitant){
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.FINAL_LOCAL_KEY, local);
        intent.putExtra(ResultActivity.FINAL_VISITANT_KEY, visitant);
        startActivity(intent);
    }
}