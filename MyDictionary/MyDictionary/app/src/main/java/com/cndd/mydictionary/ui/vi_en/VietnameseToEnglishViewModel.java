package com.cndd.mydictionary.ui.vi_en;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VietnameseToEnglishViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VietnameseToEnglishViewModel() {
        mText = new MutableLiveData<>();
//        mText.setValue("This is Vietnamese-English fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}