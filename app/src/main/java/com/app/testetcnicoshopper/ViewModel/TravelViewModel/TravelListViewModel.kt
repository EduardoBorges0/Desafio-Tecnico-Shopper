package com.app.testetcnicoshopper.ViewModel.TravelViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.RidesDTO
import com.app.testetcnicoshopper.Model.DTO.TravelListDTO.TravelListDTO
import com.app.testetcnicoshopper.Model.Repositories.TravelListRepositories.TravelListRepositories
import com.app.testetcnicoshopper.View.components.formatDateAndTime
import kotlinx.coroutines.launch
import retrofit2.Response

import com.google.gson.JsonParser

class TravelListViewModel(private val repositories: TravelListRepositories) : ViewModel() {
    private var _erroMessage = MutableLiveData<String>()
    var erroMessage : LiveData<String> = _erroMessage

    var alertDialog by mutableStateOf(false)
    private var _responseMessage = MutableLiveData<Response<TravelListDTO>>()
    var responseMessage : LiveData<Response<TravelListDTO>> = _responseMessage

    private val _filterState = MutableLiveData<Boolean>()
    val filterState: LiveData<Boolean> get() = _filterState

    private val _filteredRides = MutableLiveData<List<RidesDTO>>()
    val filteredRides: LiveData<List<RidesDTO>> get() = _filteredRides

    fun getTravelList(customer_id: String, driverId: String){
        viewModelScope.launch {
            val response = repositories.getTravelList(customer_id, driverId)
            if(response.isSuccessful){
                _responseMessage.value = response
                Log.d("ResponseTravelList", "Response Travel list: ${responseMessage.value?.body()?.rides?.map { formatDateAndTime(it.date.toString()) }}")
            }else{
                alertDialog = true
                val errorBody = response.errorBody()?.string()
                val errorDescription = parseErrorDescription(errorBody)
                _erroMessage.value = errorDescription
            }
        }
    }

    fun applyFilters(
        rides: List<RidesDTO>?,
        driverName: String = "",
        rideValue: String = "",
        origin: String = "",
        destination: String = "",
        distance: String = "",
        date: String = ""
    ) {
        val filtered = rides?.filter { ride ->
            (driverName.isEmpty() || ride.driver.name.contains(driverName, ignoreCase = true)) &&
                    (rideValue.isEmpty() || ride.value.toString().contains(rideValue, ignoreCase = true)) &&
                    (origin.isEmpty() || ride.origin.contains(origin, ignoreCase = true)) &&
                    (destination.isEmpty() || ride.destination.contains(destination, ignoreCase = true))
        } ?: emptyList()
        _filteredRides.value = filtered
        _filterState.value = true
        Log.d("FILTER", "DRIVERS FILTER: ${filteredRides.value}")
    }
    fun clearFilter() {
        viewModelScope.launch {
            _filterState.value = false
            _filteredRides.value = responseMessage.value?.body()?.rides ?: emptyList()
        }
    }

    private fun parseErrorDescription(errorBody: String?): String {
        return try {
            val jsonObject = JsonParser.parseString(errorBody).asJsonObject
            jsonObject.get("error_description")?.asString ?: "Erro desconhecido"
        } catch (e: Exception) {
            "Erro ao processar a resposta"
        }
    }
}
