package com.ikariscraft.stepscounter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Integer> score = new MutableLiveData<>();

    public MainViewModel(){
        score.setValue(0);
    }

    public LiveData<Integer> getScore(){
        return score;
    }

    void addPoints(int points){

        score.setValue(score.getValue()+points);
    }

    void decreasePoints(){
        if(score.getValue()>0){
            score.setValue(score.getValue()-1);
        }
    }
}
