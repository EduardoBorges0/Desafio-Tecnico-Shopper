package com.app.testetcnicoshopper.Model.Repositories.TravelListRepositories

import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.TravelListDTO
import com.app.testetcnicoshopper.Model.RetroFit.TravelListAPI.TraveListAPI
import retrofit2.Response

class TravelListRepositories(private val traveListAPI: TraveListAPI) {
    suspend fun getTravelList(customer_id: String, driverId: String) : Response<TravelListDTO>{
        return traveListAPI.getTravelList(customer_id, driverId)
    }
}
