package com.app.testetcnicoshopper.Model.Repositories.TripRepositories

import com.app.testetcnicoshopper.Model.DTO.TripDTO.ResponseTripDTO
import com.app.testetcnicoshopper.Model.DTO.TripDTO.TripDTO
import com.app.testetcnicoshopper.Model.RetroFit.ConfirmTripAPI.ConfirmTripAPI
import com.app.testetcnicoshopper.Model.entities.TripEntities.TripResponse

class TripeRepositories(private val confirmTripAPI: ConfirmTripAPI) {
    suspend fun tripRepositories(confirmTripModel: TripDTO) : ResponseTripDTO{
        return confirmTripAPI.confirmTrip(confirmTripModel)
    }
}
