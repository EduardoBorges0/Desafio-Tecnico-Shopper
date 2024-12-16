package com.app.testetcnicoshopper.Model.entities.TripEntities

data class TripModel (
    val customer_id : String,
    val origin : String?,
    val destination : String?,
    val distance : Double,
    val duration : String,
    val driver : Driver,
    val value : Double
)

data class Driver(
    val id : Int,
    val name : String
)
