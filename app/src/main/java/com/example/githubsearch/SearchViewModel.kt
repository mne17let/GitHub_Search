package com.example.githubsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

    val mutableLiveData: MutableLiveData<String> = MutableLiveData()

    fun search(textFromEditor: String){
        mutableLiveData.value = textFromEditor
    }
}