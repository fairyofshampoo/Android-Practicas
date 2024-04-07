package com.ikariscraft.earthquakes.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ikariscraft.earthquakes.Earthquake;
import com.ikariscraft.earthquakes.api.RequestStatus;
import com.ikariscraft.earthquakes.api.StatusWithDescription;
import com.ikariscraft.earthquakes.database.EqDatabase;
import com.ikariscraft.earthquakes.main.MainRepository;

import java.util.List;


public class MainViewModel extends AndroidViewModel {
    public final MainRepository repository;
    public LiveData<StatusWithDescription> getStatusMutableLiveData(){
        return statusMutableLiveData;
    }

    private MutableLiveData<StatusWithDescription> statusMutableLiveData = new
            MutableLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
        EqDatabase database = EqDatabase.getDatabase(application);
        repository = new MainRepository(database);
    }

    public LiveData<List<Earthquake>> getEqList(){
        return repository.getEqList();
    }

    public void downloadEarthquakes() {
        statusMutableLiveData.setValue(new StatusWithDescription(RequestStatus.LOADING,""));
        repository.downloadAndSaveEarthquakes(new MainRepository.DownloadStatusListener() {
            @Override
            public void downloadSuccess() {
                statusMutableLiveData.setValue(new StatusWithDescription(RequestStatus.DONE,""));
            }
            @Override
            public void downloadError(String message) {
                statusMutableLiveData.setValue(new StatusWithDescription(RequestStatus.LOADING, message));
            }
        });
    }

}
