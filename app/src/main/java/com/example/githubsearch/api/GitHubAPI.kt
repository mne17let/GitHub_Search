package com.example.githubsearch.api

import com.example.githubsearch.Model.GitHubResponseUsers
import com.example.githubsearch.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubAPI {

    @GET("/search/users")
    fun getSearchListUsers(@Query("q") searchString: String) : Call<GitHubResponseUsers>
}