package com.ikariscraft.earthquakes.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.ikariscraft.earthquakes.Earthquake;
import com.ikariscraft.earthquakes.databinding.ActivityMainBinding;
import com.ikariscraft.earthquakes.details.DetailActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        MainViewModel viewModel = new ViewModelProvider(this,
            new MainViewModelFactory(getApplication())).get(MainViewModel.class);

        binding.eqRecycler.setLayoutManager(new LinearLayoutManager(this));

        EqAdapter adapter = new EqAdapter();
        adapter.setOnItemClickListener(earthquake ->{
                openDetailActivity(earthquake);
        });
        binding.eqRecycler.setAdapter(adapter);

        viewModel.downloadEarthquakes();

        viewModel.getEqList().observe(this, eqList -> {

            adapter.submitList(eqList);
        });
    }

    private void openDetailActivity(Earthquake earthquake){
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("MAGNITUDE", earthquake.getMagnitude());
        intent.putExtra("PLACE", earthquake.getPlace());
        intent.putExtra("ID", earthquake.getId());
        startActivity(intent);
    }
}