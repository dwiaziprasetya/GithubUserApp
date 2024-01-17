package com.example.githubuserapp.retrofit

import com.example.githubuserapp.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/search/users")
    @Headers("Authorization: token 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")
    fun getSearch(
        @Query("q") username :String
    ) : Call<SearchResponse>
}