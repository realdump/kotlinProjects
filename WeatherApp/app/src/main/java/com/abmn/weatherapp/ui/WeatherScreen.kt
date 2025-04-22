package com.abmn.weatherapp.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.abmn.weatherapp.data.RetrofitInstance
import com.abmn.weatherapp.utils.Constants
import kotlinx.coroutines.launch

@Composable
fun WeatherScreen() {
    var city by remember { mutableStateOf(TextFieldValue("")) }
    var weatherInfo by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City Name") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    isLoading = true
                    errorMessage = null
                    try {
                        val response = RetrofitInstance.api.getWeather(city.text, Constants.API_KEY)
                        weatherInfo = "City: ${response.name}\nTemp: ${response.main.temp}°C\nCondition: ${response.weather[0].description}"
                    } catch (e: Exception) {
                        errorMessage = "City not found or network error."
                        weatherInfo = null
                    }
                    isLoading = false
                }
            }
        ) {
            Text("Get Weather")
        }

        Spacer(modifier = Modifier.height(24.dp))

        when {
            isLoading -> CircularProgressIndicator()
            errorMessage != null -> Text(text = errorMessage!!, color = MaterialTheme.colorScheme.error)
            weatherInfo != null -> Text(text = weatherInfo!!)
        }
    }
}
