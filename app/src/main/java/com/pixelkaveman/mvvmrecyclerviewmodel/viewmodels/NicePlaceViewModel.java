package com.pixelkaveman.mvvmrecyclerviewmodel.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.viewpager.widget.ViewPager;

import com.pixelkaveman.mvvmrecyclerviewmodel.models.NicePlace;
import com.pixelkaveman.mvvmrecyclerviewmodel.repositories.NicePlaceRepository;

import java.util.List;

public class NicePlaceViewModel  extends ViewModel {

    private MutableLiveData<List<NicePlace>> mNicePlaces ;

    private NicePlaceRepository mRepo;

    // we wnat ro retrieve the data
    // inside init()
    public void init(){

        if (mNicePlaces != null ){
            return ;
        }
        mRepo = NicePlaceRepository.getInstance();
        mNicePlaces = mRepo.getNicePlaces();
    }

    public LiveData<List<NicePlace>> getNicePlaces(){
        return mNicePlaces;
    }
}
