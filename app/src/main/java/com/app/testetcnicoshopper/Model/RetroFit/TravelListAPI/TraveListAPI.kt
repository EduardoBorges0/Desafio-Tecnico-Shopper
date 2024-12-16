package com.app.testetcnicoshopper.Model.RetroFit.TravelListAPI

import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.TravelListDTO
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TraveListAPI {
    @GET("ride/{customer_id}")
    suspend fun getTravelList(
        @Path("customer_id") customerId: String,
        @Query("driver_id") driverId: String
    ): Response<TravelListDTO>

    companion object{
        private val retroFitRepositories : TraveListAPI by lazy {
            val gson: Gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")  // The format that matches the date string you provided
                .create()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/")
                .addConverterFactory(GsonConverterFactory.create(gson)) // Use custom Gson with Date format
                .build()

            retrofit.create(TraveListAPI::class.java)
        }
        fun getInstance() : TraveListAPI{
            return retroFitRepositories
        }
    }
}
