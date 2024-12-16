package com.app.testetcnicoshopper.Model.entities.TravelList

import java.util.Date

data class TravelListModelResponse (
   val customer_id : String,
   val rides: List<Rides>
)
data class Rides(
    val id: Int,
    val date: Date,
    val origin: String,
    val destination: String,
    val distance : Double,
    val duration : String,
    val driver: DriverTravelList,
    val value: Double
)

data class DriverTravelList(
    val id: Int,
    val name: String
)
