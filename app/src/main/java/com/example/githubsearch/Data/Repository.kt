package com.example.githubsearch.Data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.githubsearch.Model.User
import com.example.githubsearch.SearchViewModel
import com.example.githubsearch.TAG_FOR_SEARCH_USERS

class Repository {

    private lateinit var callBackFromViewModel_Users: SearchViewModel.CallBackFromViewModel

    private var usersList: List<User> = mutableListOf()
    private val dataAPI: DataAPI = DataAPI()

    fun searchUsers(searchString: String, callBack: SearchViewModel.CallBackFromViewModel){
        Log.d(TAG_FOR_SEARCH_USERS, "В repository вызван searchUsers. Текст запроса - $searchString")

        callBackFromViewModel_Users = callBack

        dataAPI.downLoadUsers(searchString, CallBackFromRepository())
    }

    inner class CallBackFromRepository{
        fun updateUsersListLiveData(list: List<User>){
            usersList = list
            callBackFromViewModel_Users.updateUsersListLiveData(list)
        }
    }
}