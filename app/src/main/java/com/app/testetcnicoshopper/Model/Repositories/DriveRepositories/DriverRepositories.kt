package com.app.testetcnicoshopper.Model.Repositories.DriveRepositories

import android.util.Log
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import com.app.testetcnicoshopper.Model.RetroFit.SearchDriveAPI.DrivePOST
import com.app.testetcnicoshopper.Model.DTO.DriverDTO.DriverDTO
import retrofit2.Response

open class DriverRepositories(private val drivePOST: DrivePOST) {
     open suspend fun SearchDriver(DriverDTO: DriverDTO): Response<RouteResponse> {
        Log.d("RESPONSE REPOSITORIES", "RESPONSE REPOSITORIES ${DriverDTO}")
        return drivePOST.searchDriver(DriverDTO)
    }
}
