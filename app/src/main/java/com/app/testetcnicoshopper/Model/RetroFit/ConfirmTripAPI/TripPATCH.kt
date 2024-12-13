package com.app.testetcnicoshopper.Model.RetroFit.ConfirmTripAPI

import com.app.testetcnicoshopper.Model.DTO.TripDTO.ResponseTripDTO
import com.app.testetcnicoshopper.Model.DTO.TripDTO.TripDTO
import com.app.testetcnicoshopper.Model.entities.TripEntities.TripResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.PATCH

interface ConfirmTripAPI {
    @PATCH("ride/confirm")
    suspend fun confirmTrip(@Body confirmTripModel: TripDTO) : ResponseTripDTO

    companion object{
        val retroFitInstance : ConfirmTripAPI by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(ConfirmTripAPI::class.java)
        }
        fun getInstance() : ConfirmTripAPI {
            return retroFitInstance
        }
    }
}
