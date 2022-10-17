package com.org.datasource.network.interfaces

import com.org.datasource.constant.Constants
import com.org.datasource.network.models.searchModel.CategoryItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

internal interface MultiSearchApi {


    @GET("/3/search/multi")
    fun searchMovies(@Query("query") key: String,@Query("api_key") apikey: String) : Call<CategoryItem>

    companion object Factory {
        fun create(): MultiSearchApi {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .build()
            return retrofit.create(MultiSearchApi::class.java);
        }
    }
}