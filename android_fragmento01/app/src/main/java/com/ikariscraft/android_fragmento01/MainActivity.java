package com.ikariscraft.android_fragmento01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ikariscraft.android_fragmento01.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    int screen;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        screen = 1;
        controlButtons();

        binding.imageButtonForward.setOnClickListener(v->{
            screen = 2;
            controlButtons();
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragmentContainer.getId(), new Screen2Fragment())
                    .addToBackStack(null)
                    .commit();
        });
        binding.imageButtonBack.setOnClickListener(v->{
            screen = 1;
            controlButtons();
            getSupportFragmentManager().beginTransaction()
                    .replace(binding.fragmentContainer.getId(), new Screen1Fragment())
                    .addToBackStack(null)
                    .commit();
        });
    }

    private void controlButtons(){
        if(screen==1){
            binding.imageButtonBack.setEnabled(false);
            binding.imageButtonForward.setEnabled(true);
        }else{
            binding.imageButtonBack.setEnabled(true);
            binding.imageButtonForward.setEnabled(false);
        }
    }
}