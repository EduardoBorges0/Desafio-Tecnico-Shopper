package com.app.testetcnicoshopper.View.components

import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDateAndTime(date: String): Pair<String?, String?> {
    val inputFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH) // Formato de entrada
    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Formato desejado para a data
    val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault()) // Formato desejado para a hora
    return try {
        val parsedDate = inputFormat.parse(date) // Analisa a string para Date
        if (parsedDate != null) {
            val formattedDate = dateFormat.format(parsedDate) // Formata a data
            val formattedTime = timeFormat.format(parsedDate) // Formata a hora
            Pair(formattedDate, formattedTime) // Retorna data e hora como um par
        } else {
            Pair(null, null) // Retorna nulos se a análise falhar
        }
    } catch (e: Exception) {
        Log.e("DateFormatError", "Erro ao formatar a data: $date", e)
        Pair(null, null) // Retorna nulos em caso de erro
    }
}
