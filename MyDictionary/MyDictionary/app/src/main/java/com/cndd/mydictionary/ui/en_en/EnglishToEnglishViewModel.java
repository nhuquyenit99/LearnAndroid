package com.cndd.mydictionary.ui.en_en;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EnglishToEnglishViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EnglishToEnglishViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is English-English fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }
}