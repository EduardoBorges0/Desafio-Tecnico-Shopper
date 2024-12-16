package com.app.testetcnicoshopper.Model.RetroFit.SearchDriveAPI

import com.app.testetcnicoshopper.Model.DTO.DriverDTO.DriverDTO
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface DrivePOST {
    @POST("ride/estimate")
    suspend fun searchDriver(@Body searchDriver: DriverDTO) : Response<RouteResponse>
    companion object{
        private val retroFitService : DrivePOST by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://xd5zl5kk2yltomvw5fb37y3bm40vsyrx.lambda-url.sa-east-1.on.aws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(DrivePOST::class.java)
        }
        fun getInstance() : DrivePOST {

            return retroFitService
        }
    }
}
