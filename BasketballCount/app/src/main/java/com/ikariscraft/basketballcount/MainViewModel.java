package com.ikariscraft.basketballcount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private final MutableLiveData<Integer> localScore = new MutableLiveData<>();
    private final MutableLiveData<Integer> visitantScore = new MutableLiveData<>();

    public LiveData<Integer> getLocalScore(){
        return localScore;
    }

    public LiveData<Integer> getVisitantScore(){
        return visitantScore;
    }

    public MainViewModel(){
        localScore.setValue(0);
        visitantScore.setValue(0);
    }

    void addLocalPoints(int points){
        localScore.setValue(localScore.getValue() + points);
    }

    void decreaseLocalPoints(){
        if(localScore.getValue()>0){
            localScore.setValue(localScore.getValue()-1);
        }
    }

    void addVisitantPoints(int points){
        visitantScore.setValue(visitantScore.getValue() + points);
    }

    void decreaseVisitantPoints(){
        if(visitantScore.getValue()>0){
            visitantScore.setValue(visitantScore.getValue()-1);
        }
    }

    void resetScore(){
        visitantScore.setValue(0);
        localScore.setValue(0);
    }
}
