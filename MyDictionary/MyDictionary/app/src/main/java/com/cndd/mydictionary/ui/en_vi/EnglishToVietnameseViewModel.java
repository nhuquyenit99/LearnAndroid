package com.cndd.mydictionary.ui.en_vi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EnglishToVietnameseViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EnglishToVietnameseViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}