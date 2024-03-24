package com.rkm.weatherapp

import com.rkm.weatherapp.model.currentweatherdata
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface Apiinterface {

    @GET("weather?")

    suspend fun getweatherdata(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("apikey") apikey: String,

    ): Response<currentweatherdata>
}