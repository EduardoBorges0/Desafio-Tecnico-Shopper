package com.app.testetcnicoshopper.ViewModel.DriveViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testetcnicoshopper.Model.Repositories.DriveRepositories.DriverRepositories
import com.app.testetcnicoshopper.Model.DTO.DriverDTO.DriverDTO
import com.app.testetcnicoshopper.Model.entities.DriveEntities.ErrorResponse
import com.app.testetcnicoshopper.Model.entities.DriveEntities.RouteResponse
import com.google.gson.Gson
import kotlinx.coroutines.launch
import retrofit2.Response

class DriveViewModel(private val searchDriverRepository: DriverRepositories) : ViewModel() {

    private val _searchResult = MutableLiveData<Response<RouteResponse>>()
    val searchResult: LiveData<Response<RouteResponse>> = _searchResult

    var alertDialog by mutableStateOf(false)
    var verifyError by mutableStateOf(false)

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun searchDriver(driverDTO: DriverDTO) {
        viewModelScope.launch {
            try {
                val result = searchDriverRepository.SearchDriver(driverDTO)
                if (result.isSuccessful) {
                    _searchResult.value = result
                    verifyError = false
                    Log.d("OriginToDestinQuestionSucess", "Success: ${result.body()}")
                    if (result.body()?.options?.isEmpty() == true) {
                        verifyError = true
                        _errorMessage.value = "Sem motoristas disponíveis."
                    }
                } else {
                    val errorBody = result.errorBody()?.string()
                    verifyError = true
                    _errorMessage.value = handleError(errorBody)
                    Log.e("OriginToDestinQuestion400Error", "Error 400: ${_errorMessage.value}")
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                verifyError = true
                Log.e("OriginToDestinQuestionException", "Exception: ${e.message}")
            }
        }
    }

    private fun handleError(errorBody: String?): String {
        return try {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBody, ErrorResponse::class.java)
            errorResponse.error_description
        } catch (e: Exception) {
            "Unknown error occurred"
        }
    }
}
