package com.example.githubsearch.Data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.githubsearch.Model.GitHubResponseUsers
import com.example.githubsearch.Model.User
import com.example.githubsearch.TAG_FOR_SEARCH_USERS
import com.example.githubsearch.api.GitHubAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG_FROM_DATA_API = "DataAPI"

class DataAPI {

    private lateinit var callBackFromRepository_Users: Repository.CallBackFromRepository

    private lateinit var retrofit: Retrofit
    private lateinit var gitHubAPI: GitHubAPI

    private val baseUrl = "https://api.github.com"

    init {
        retrofit = Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gitHubAPI = retrofit.create(GitHubAPI::class.java)
    }

    fun downLoadUsers(searchText: String, callback: Repository.CallBackFromRepository){
        Log.d(TAG_FOR_SEARCH_USERS, "В dataAPI вызван downLoadUsers. Текущий запрос - $searchText")

        callBackFromRepository_Users = callback

        val searchUsersRequest: Call<GitHubResponseUsers> = gitHubAPI.getSearchListUsers(searchText)

        doWebRequest(searchUsersRequest)
    }

    fun doWebRequest(request: Call<GitHubResponseUsers>){

        var usersList: List<User> = mutableListOf()

        val callBackSearchUsers = object : Callback<GitHubResponseUsers>{
            override fun onResponse(
                call: Call<GitHubResponseUsers>,
                response: Response<GitHubResponseUsers>
            ) {
                val responseBody: GitHubResponseUsers? = response.body()
                val itemsUsersList: List<User>? = responseBody?.items
                usersList = itemsUsersList ?: emptyList()


                Log.d(TAG_FOR_SEARCH_USERS, "В dataAPI выполнен поиск пользователей. " +
                        "Текущий список пользователей - ${usersList}")

                Log.d(TAG_FROM_DATA_API, "Список в dataAPI: ${usersList}")

                Log.d(TAG_FROM_DATA_API, "Получен ответ: ${response.body()}")

                Log.d(TAG_FROM_DATA_API, "Получен пользователь: ${usersList?.get(1)?.html_url}")

                Log.d(TAG_FROM_DATA_API, "Размер списка: ${usersList.size}")

                Log.d(TAG_FROM_DATA_API, "Получен список: ${usersList}")

                callBackFromRepository_Users.updateUsersListLiveData(usersList)
            }

            override fun onFailure(call: Call<GitHubResponseUsers>, t: Throwable) {
                Log.d(TAG_FROM_DATA_API, "Получен ответ: $t")
            }
        }

        request.enqueue(callBackSearchUsers)
    }
}


