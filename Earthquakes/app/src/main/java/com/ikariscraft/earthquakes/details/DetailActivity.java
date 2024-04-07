package com.ikariscraft.earthquakes.details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ikariscraft.earthquakes.Earthquake;
import com.ikariscraft.earthquakes.R;
import com.ikariscraft.earthquakes.databinding.ActivityDetailBinding;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {
    public static final String EARTHQUAKE_KEY = "earthquake_key";
    ActivityDetailBinding binding;
    private MapView mMapView;
    private GoogleMap mMap;
    Earthquake earthquake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle extras = getIntent().getExtras();
        earthquake = extras.getParcelable("earthquake_key");

        Date date = new Date(earthquake.getTime());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String formattedDate = simpleDateFormat.format(date);
        
        binding.setEarthquake(earthquake);
        binding.setTime(formattedDate);
        binding.setMag(String.format(String.valueOf(R.string.magnitude_format), earthquake.getMagnitude()));

        mMapView = binding.mapView;
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        if (mMap != null) {
            LatLng loc = new LatLng(earthquake.getLongitude(), earthquake.getLatitude());
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(loc)
                    .title(earthquake.getPlace());

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 3));
        }
    }
}