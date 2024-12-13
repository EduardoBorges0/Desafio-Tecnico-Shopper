package com.app.testetcnicoshopper.Model.DTO.TripDTO

import com.app.testetcnicoshopper.Model.entities.TripEntities.Driver
import com.app.testetcnicoshopper.Model.entities.TripEntities.TripModel

data class TripDTO(
    val customer_id: String,
    val origin: String?,
    val destination: String?,
    val distance: Double,
    val duration: String,
    val driver: DriverTripDTO, // Agora é um único objeto DriverTripDTO
    val value: Double
)

data class DriverTripDTO(
    val id: Int,
    val name: String
)

fun Driver.toDriverTripDTO(): DriverTripDTO {
    return DriverTripDTO(
        id = this.id,
        name = this.name
    )
}

fun DriverTripDTO.toDriver(): Driver {
    return Driver(
        id = this.id,
        name = this.name
    )
}

fun TripModel.toDTO(): TripDTO {
    return TripDTO(
        customer_id = this.customer_id,
        origin = this.origin,
        destination = this.destination,
        distance = this.distance,
        duration = this.duration,
        driver = this.driver.toDriverTripDTO(), // Ajustado para refletir um único objeto
        value = this.value
    )
}

fun TripDTO.toModel(): TripModel {
    return TripModel(
        customer_id = this.customer_id,
        origin = this.origin,
        destination = this.destination,
        distance = this.distance,
        duration = this.duration,
        driver = this.driver.toDriver(), // Ajustado para refletir um único objeto
        value = this.value
    )
}
