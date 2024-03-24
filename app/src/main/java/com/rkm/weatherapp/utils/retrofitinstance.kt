package com.rkm.weatherapp.utils

import com.rkm.weatherapp.Apiinterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitinstance {

    val api:Apiinterface by lazy {
        Retrofit.Builder().baseUrl(utils.base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Apiinterface::class.java)
    }
}