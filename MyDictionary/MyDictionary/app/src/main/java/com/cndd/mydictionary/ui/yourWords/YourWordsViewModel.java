package com.cndd.mydictionary.ui.yourWords;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YourWordsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public YourWordsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is your words fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}