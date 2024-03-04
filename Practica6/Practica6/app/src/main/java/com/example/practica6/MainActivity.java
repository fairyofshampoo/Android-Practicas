package com.example.practica6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.example.practica6.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.recycler.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Note> list = new ArrayList<>();
        for(int i = 1; i <= 20; i++){
            list.add(new Note(i, "Nota "+i, "Aquí se detalla la nota"));
        }
        NoteAdapter adapter = new NoteAdapter();
        binding.recycler.setAdapter(adapter);
        adapter.submitList(list);
        adapter.setOnItemClickListener(record ->{
            Toast.makeText(MainActivity.this, record.getDescription(),
                    Toast.LENGTH_SHORT).show();
        });
    }

}