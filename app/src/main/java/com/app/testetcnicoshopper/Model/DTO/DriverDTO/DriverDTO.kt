package com.app.testetcnicoshopper.Model.DTO.DriverDTO

import com.app.testetcnicoshopper.Model.entities.DriveEntities.DriverModel

data class DriverDTO(
    val customer_id: String,
    val origin: String,
    val destination: String
)

fun DriverModel.toDTO(): DriverDTO {
    return DriverDTO(
        customer_id = this.customer_id,
        origin = this.origin,
        destination = this.destination
    )
}

fun DriverDTO.toModel(): DriverModel {
    return DriverModel(
        customer_id = this.customer_id,
        origin = this.origin,
        destination = this.destination
    )
}
