package com.ikariscraft.earthquakes.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.ikariscraft.earthquakes.Earthquake;
import com.ikariscraft.earthquakes.api.RequestStatus;
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

        EqAdapter adapter = new EqAdapter(this);
        adapter.setOnItemClickListener(earthquake ->{
                openDetailActivity(earthquake);
        });
        binding.eqRecycler.setAdapter(adapter);

        viewModel.downloadEarthquakes();

        viewModel.getEqList().observe(this, eqList -> {

            adapter.submitList(eqList);
        });

        viewModel.getStatusMutableLiveData().observe(this,status->{
            if(status.getStatus() == RequestStatus.LOADING){
                binding.loadingWheel.setVisibility(View.VISIBLE);
            }else{
                binding.loadingWheel.setVisibility(View.GONE);
            }
            if(status.getStatus() ==RequestStatus.ERROR){
                Toast.makeText(this,status.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openDetailActivity(Earthquake earthquake){
        Log.d("EARTHQUAKE APP", "openDetailActivity: " + earthquake.getPlace()
                +"\nMagnitude: "+earthquake.getMagnitude()
                +"\nTime: "+earthquake.getTime());
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(DetailActivity.EARTHQUAKE_KEY, earthquake);
        startActivity(intent);
    }
}