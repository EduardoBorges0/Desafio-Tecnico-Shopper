package com.app.testetcnicoshopper.ViewModel.TripViewModel

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testetcnicoshopper.Model.DTO.TripDTO.DriverTripDTO
import com.app.testetcnicoshopper.Model.DTO.TripDTO.TripDTO
import com.app.testetcnicoshopper.Model.Repositories.TripRepositories.TripeRepositories
import kotlinx.coroutines.launch

class TripViewModel(private val repositories: TripeRepositories) : ViewModel() {
    private val _errorMessages = MutableLiveData<List<String>>()
    val errorMessages: LiveData<List<String>> = _errorMessages
    var alertDialog by mutableStateOf(false)
    var verifyError by mutableStateOf(false)
    var loading by mutableStateOf(false)
    fun verifyDriver(tripDTO: TripDTO) {
        viewModelScope.launch {
            val validationResults = TripValidator.validateTrip(tripDTO)
            if (validationResults.isNotEmpty()) {
                _errorMessages.value = validationResults
                verifyError = true

                Log.d("ValidationError", "Errors: ${errorMessages.value}")
            } else {
                val response = repositories.tripRepositories(tripDTO)
                verifyError = false
                Log.d("Response", "Response trip ${response.success}")
            }
        }
    }
}

object TripValidator {
    fun validateTrip(trip: TripDTO): List<String> {
        val errors = mutableListOf<String>()
        val driverId = trip.driver.id

        when {
            !isValidDriver(driverId) -> errors.add("Motorista inválido!")
            !isValidDistance(driverId, trip.distance) -> errors.add("Distância inválida para o motorista!")
            trip.origin.isNullOrEmpty() -> errors.add("Origem não informada!")
            trip.destination.isNullOrEmpty() -> errors.add("Destino não informado!")
            trip.origin == trip.destination -> errors.add("Origem e destino não podem ser iguais!")
        }

        return errors
    }

    private fun isValidDriver(driverId: Int?): Boolean {
        return when (driverId) {
            1, 2, 3 -> true
            else -> false
        }
    }

    private fun isValidDistance(driverId: Int?, km: Double): Boolean {
        return when (driverId) {
            1 -> km/1000 >= 1
            2 -> km/1000 >= 5
            3 -> km/1000 >= 10
            else -> true
        }
    }
}
