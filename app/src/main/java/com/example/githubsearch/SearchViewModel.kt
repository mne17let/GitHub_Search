package com.example.githubsearch

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.githubsearch.Data.Repository
import com.example.githubsearch.Model.User

class SearchViewModel: ViewModel() {

    private val mutableLiveDataSearchText: MutableLiveData<String> = MutableLiveData()
    private val repository: Repository = Repository()

    private val mutableListOfUsers: MutableLiveData<List<User>> = MutableLiveData()

    val currentUsersListViewModel: LiveData<List<User>> = Transformations
        .switchMap(mutableListOfUsers, ::myFunForTransformations)

    fun search(textFromEditText: String){
        mutableLiveDataSearchText.value = textFromEditText

        Log.d(TAG_FOR_SEARCH_USERS,
            "Во viewModel в mutableLiveDataSearchText установлено значение запроса - " +
                    "${mutableLiveDataSearchText.value}")

        Log.d(TAG_FOR_SEARCH_USERS, "Во viewModel вызван search. Текущий текст запроса - $textFromEditText")
        repository.searchUsers(textFromEditText, CallBackFromViewModel())


    }

    private fun myFunForTransformations(newList: List<User>): LiveData<List<User>>{
        Log.d(TAG_FOR_SEARCH_USERS, "Во viewModel вызвана функция для switchMap. " +
                "Переданное значение - $newList")
        val newMutableLiveData = MutableLiveData<List<User>>()
        newMutableLiveData.value = newList
        return newMutableLiveData
    }

    inner class CallBackFromViewModel{
        fun updateUsersListLiveData(list: List<User>){
            mutableListOfUsers.value = list
        }
    }
}