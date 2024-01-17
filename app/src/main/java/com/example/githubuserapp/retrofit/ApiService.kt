package com.example.githubuserapp.retrofit

import com.example.githubuserapp.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    @Headers("Authorization: token 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")
    fun getSearch(
        @Query("q") username :String
    ) : Call<SearchResponse>
}