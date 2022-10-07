package com.kemosabe.bloodstore.ui.enroll_student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class enrollstudentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public enrollstudentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}