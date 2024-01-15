package com.example.githubuserapp.retrofit

import com.example.githubuserapp.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @GET("/search/users?q={username}")
    @Headers("Authorization : token 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")
    fun getSearch(
        @Path("username") id :String
    ) : Call<SearchResponse>
}