package com.app.testetcnicoshopper.Model.DTO.TripDTO

import com.app.testetcnicoshopper.Model.entities.TripEntities.TripResponse

data class ResponseTripDTO (
    val success : Boolean
)

fun TripResponse.toDTO() : ResponseTripDTO{
    return ResponseTripDTO(
        success = this.success
    )
}
fun TripResponse.toModel() : TripResponse{
    return TripResponse(
        success = this.success
    )
}
