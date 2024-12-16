package com.app.testetcnicoshopper.Model.DTO.TravelListDTO

import com.app.testetcnicoshopper.Model.entities.TravelList.DriverTravelList
import com.app.testetcnicoshopper.Model.entities.TravelList.Rides
import com.app.testetcnicoshopper.Model.entities.TravelList.TravelListModelResponse
import java.util.Date

// Data classes DTO
data class TravelListDTO(
    val customer_id: String,
    val rides: List<RidesDTO>
)

data class RidesDTO(
    val id: Int,
    val date: Date,
    val origin: String,
    val destination: String,
    val distance: Double,
    val duration: String,
    val driver: DriverTravelListDTO,
    val value: Double
)

data class DriverTravelListDTO(
    val id: Int,
    val name: String
)
fun TravelListModelResponse.toDTO(): TravelListDTO {
    return TravelListDTO(
        customer_id = this.customer_id,
        rides = this.rides.map { it.toDTO() }
    )
}
fun Rides.toDTO(): RidesDTO {
    return RidesDTO(
        id = this.id,
        date = this.date,
        origin = this.origin,
        destination = this.destination,
        distance = this.distance,
        duration = this.duration,
        driver = this.driver.toDTO(), // Mapear o driver
        value = this.value
    )
}
fun DriverTravelList.toDTO(): DriverTravelListDTO {
    return DriverTravelListDTO(
        id = this.id,
        name = this.name
    )
}
