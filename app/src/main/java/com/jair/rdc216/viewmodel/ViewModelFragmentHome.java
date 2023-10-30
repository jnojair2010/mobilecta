package com.jair.rdc216.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jair.rdc216.fragments.frag_activit_main.Home;

public class ViewModelFragmentHome extends ViewModel {

    private MutableLiveData data;

    public ViewModelFragmentHome(Home home) {
    }

    public LiveData getData(){
        if(data==null){
            data = new MutableLiveData();
            loadUsers();
        }
        return data;
    }
    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }

}
