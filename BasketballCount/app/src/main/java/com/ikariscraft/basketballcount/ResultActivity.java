package com.ikariscraft.basketballcount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.ikariscraft.basketballcount.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {
    public static final String FINAL_LOCAL_KEY = "finallocalkey";
    public static final String FINAL_VISITANT_KEY = "finalvisitantkey";

    ActivityResultBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        binding.setFinalLocalScore(String.valueOf(extras.getInt(FINAL_LOCAL_KEY)));
        binding.setFinalVisitantScore(String.valueOf(extras.getInt(FINAL_VISITANT_KEY)));
        findResult(extras.getInt(FINAL_LOCAL_KEY), extras.getInt(FINAL_VISITANT_KEY));
    }

    private void findResult(int localFinalScore, int visitantFinalScore){
        if(localFinalScore == visitantFinalScore){
            binding.setResultText("Han empatado");
        } else{
            if(localFinalScore > visitantFinalScore){
                binding.setResultText("Equipo local ha ganado");
            }
            binding.setResultText("Equipo visitante ha ganado");
        }
    }
}