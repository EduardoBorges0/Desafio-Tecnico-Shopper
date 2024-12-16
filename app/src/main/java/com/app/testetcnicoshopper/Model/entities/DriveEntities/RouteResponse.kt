package com.app.testetcnicoshopper.Model.entities.DriveEntities

data class RouteResponse(
    val origin: Location,
    val destination: Location,
    val distance: Int,
    val duration: Int,
    val options: List<Option>,
    val routeResponse: Any
)

data class Location(
    val latitude: Double,
    val longitude: Double
)

data class Option(
    val id: Int,
    val name: String,
    val description: String,
    val vehicle: String,
    val review: Review,
    val value: Double
)

data class Review(
    val rating: Int,
    val comment: String
)
