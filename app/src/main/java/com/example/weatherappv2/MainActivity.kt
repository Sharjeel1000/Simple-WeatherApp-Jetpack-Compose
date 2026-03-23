//package com.example.weatherappv2
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.enableEdgeToEdge
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.WbSunny
//import androidx.compose.material3.Button
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextField
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.android.volley.Request
//import com.android.volley.toolbox.JsonObjectRequest
//import com.android.volley.toolbox.Volley
//import com.example.weatherappv2.ui.theme.WeatherAppV2Theme
//
//var temperatureOfCity by mutableStateOf("--°")
//var skyCondition by mutableStateOf("Loading...")
//var humidityVal by mutableStateOf("--%")
//var windVal by mutableStateOf("-- Km/h")
//var pressureVal by mutableStateOf("-- hPa")
//var cityName by mutableStateOf("Chakwal")
//
//var text by mutableStateOf("")
//
//class MainActivity : ComponentActivity()
//{
//
//    val API_KEY: String = "86c24c294ac934d0a14219f9477452a8"
//    // 2. Fix the API Link to actually use the API_KEY variable using string interpolation ($API_KEY)
//    val API_Link: String = "https://api.openweathermap.org/data/2.5/weather?q=$cityName,PK&units=metric&appid=$API_KEY"
//
//    override fun onCreate(savedInstanceState: Bundle?)
//    {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        // 3. Make the Network Request
//        fetchWeatherData()
//
//        setContent {
//            WeatherAppV2Theme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Box(modifier = Modifier.padding(innerPadding)) {
//                        MainScreen()
//                    }
//                }
//            }
//        }
//    }
//
//    private fun fetchWeatherData() {
//        val requestQueue = Volley.newRequestQueue(this)
//
//        val jsonObjectRequest = JsonObjectRequest(
//            Request.Method.GET, API_Link, null,
//            { response ->
//                try {
//                    // 4. Parse the JSON Response according to OpenWeatherMap structure
//                    val mainObject = response.getJSONObject("main")
//                    val weatherArray = response.getJSONArray("weather")
//                    val weatherObject = weatherArray.getJSONObject(0)
//                    val windObject = response.getJSONObject("wind")
//
//                    // Extract values
//                    val temp = mainObject.getDouble("temp").toInt() // convert 25.02 to 25
//                    val mainWeather = weatherObject.getString("main") // e.g., "Clouds"
//                    val humidity = mainObject.getInt("humidity")
//                    val windSpeed = windObject.getDouble("speed")
//                    val pressure = mainObject.getInt("pressure")
//
//                    // 5. Update Compose States (This triggers UI refresh)
//                    temperatureOfCity = "$temp°"
//                    skyCondition = mainWeather
//                    humidityVal = "$humidity%"
//                    windVal = "$windSpeed Km/h"
//                    pressureVal = "$pressure hPa"
//
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    skyCondition = "Error parsing data"
//                }
//            },
//            { error ->
//                error.printStackTrace()
//                skyCondition = "Network Error"
//            }
//        )
//
//        requestQueue.add(jsonObjectRequest)
//    }
//}
//
//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun MainScreen() {
//    Box(modifier = Modifier
//        .fillMaxSize()
//        .background(
//            Brush.verticalGradient(
//                colors = listOf(
//                    Color(0xFF5B8CFF),
//                    Color(0xFF7B61FF)
//                )
//            )
//        )
//        .padding(25.dp)
//    ) {
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            verticalArrangement = Arrangement.SpaceBetween
//        ) {
//            CitySection()
//            WeatherMainSection()
//            WeatherStatsSection()
//        }
//    }
//}
//
//@Composable
//fun CitySection()
//{
////    Text(
////        text = "Chakwal",
////        fontSize = 32.sp,
////        fontWeight = FontWeight.Bold,
////        color = Color.White
////    )
//      Column()
//      {
//          Box(modifier = Modifier
//              .fillMaxWidth()
//              .height(70.dp)
//              .padding(9.dp)
//              .background(color = Color.Blue,
//                  shape = RoundedCornerShape(20.dp)))
//          {
//
//
//              TextField(
//                  modifier = Modifier
//                      .fillMaxSize(),
//                  value = text, // 2. Display the current value
//                  onValueChange = { newText -> // 3. Update the state when text changes
//                      text = newText
//                  },
//                  label = { Text("Enter City Name") }, // Optional label
//                  singleLine = true, // Optional constraint for single-line input
//                  shape = RoundedCornerShape(16.dp)
//              )
//          }
//
//          Button(
//              onClick = {
//                  if (!text.isEmpty())
//                  {
//                      cityName = text
//
//                  }
//              }
//          )
//          {
//              Text(text = "Search")
//          }
//      }
//
//}
//
//@Composable
//fun WeatherMainSection() {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        // Reads from the mutable state
//        Text(
//            text = temperatureOfCity,
//            fontSize = 96.sp,
//            fontWeight = FontWeight.Light,
//            color = Color.White
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        Icon(
//            imageVector = Icons.Default.WbSunny,
//            contentDescription = "Weather Icon",
//            tint = Color.White,
//            modifier = Modifier.size(72.dp)
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        // Reads from the mutable state
//        Text(
//            text = skyCondition,
//            fontSize = 20.sp,
//            color = Color.White.copy(alpha = 0.9f)
//        )
//    }
//}
//
//@Composable
//fun WeatherStatsSection() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 12.dp),
//        horizontalArrangement = Arrangement.SpaceBetween
//    ) {
//        // Reads from the mutable states
//        WeatherStatItem("Humidity", humidityVal)
//        WeatherStatItem("Wind", windVal)
//        WeatherStatItem("Pressure", pressureVal)
//    }
//}
//
//@Composable
//fun WeatherStatItem(label: String, value: String) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = value,
//            fontSize = 18.sp,
//            fontWeight = FontWeight.SemiBold,
//            color = Color.White
//        )
//        Text(
//            text = label,
//            fontSize = 14.sp,
//            color = Color.White.copy(alpha = 0.7f)
//        )
//    }
//}




package com.example.weatherappv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.weatherappv2.ui.theme.WeatherAppV2Theme

// --- State Variables ---
var temperatureOfCity by mutableStateOf("--°")
var skyCondition by mutableStateOf("Loading...")
var humidityVal by mutableStateOf("--%")
var windVal by mutableStateOf("-- Km/h")
var pressureVal by mutableStateOf("-- hPa")
var cityName by mutableStateOf("Chakwal")
var textFieldValue by mutableStateOf("")

class MainActivity : ComponentActivity()
{

    private val API_KEY = "86c24c294ac934d0a14219f9477452a8"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initial fetch
        fetchWeatherData(cityName)

        setContent {
            WeatherAppV2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        MainScreen(onSearchClick = { newCity ->
                            fetchWeatherData(newCity)
                        })
                    }
                }
            }
        }
    }

    private fun fetchWeatherData(city: String) {
        val queue = Volley.newRequestQueue(this)
        // URL is now built inside the function to use the latest city name
        val url = "https://api.openweathermap.org/data/2.5/weather?q=$city&units=metric&appid=$API_KEY"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                try {
                    val main = response.getJSONObject("main")
                    val weather = response.getJSONArray("weather").getJSONObject(0)
                    val wind = response.getJSONObject("wind")

                    // Update global states
                    cityName = response.getString("name")
                    temperatureOfCity = "${main.getDouble("temp").toInt()}°"
                    skyCondition = weather.getString("main")
                    humidityVal = "${main.getInt("humidity")}%"
                    windVal = "${wind.getDouble("speed")} Km/h"
                    pressureVal = "${main.getInt("pressure")} hPa"
                } catch (e: Exception) {
                    skyCondition = "Parse Error"
                }
            },
            { error ->
                skyCondition = "City Not Found"
            }
        )
        queue.add(jsonObjectRequest)
    }
}

@Composable
fun MainScreen(onSearchClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF5B8CFF), Color(0xFF7B61FF))
                )
            )
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            CitySection(onSearchClick)
            WeatherMainSection()
            WeatherStatsSection()
        }
    }
}

@Composable
fun CitySection(onSearchClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = cityName,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { textFieldValue = it },
            label = { Text("Search City", color = Color.White.copy(alpha = 0.7f)) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White.copy(alpha = 0.5f)
            ),
            trailingIcon = {
                IconButton(onClick = {
                    if (textFieldValue.isNotEmpty()) {
                        onSearchClick(textFieldValue)
                    }
                }) {
                    Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                }
            }
        )
    }
}

@Composable
fun WeatherMainSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = temperatureOfCity,
            fontSize = 96.sp,
            fontWeight = FontWeight.Light,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(8.dp))
        Icon(
            imageVector = Icons.Default.WbSunny,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(72.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = skyCondition,
            fontSize = 24.sp,
            color = Color.White.copy(alpha = 0.9f)
        )
    }
}

@Composable
fun WeatherStatsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherStatItem("Humidity", humidityVal)
        WeatherStatItem("Wind", windVal)
        WeatherStatItem("Pressure", pressureVal)
    }
}

@Composable
fun WeatherStatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = value, fontSize = 18.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
        Text(text = label, fontSize = 14.sp, color = Color.White.copy(alpha = 0.7f))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMain() {
    WeatherAppV2Theme {
        MainScreen(onSearchClick = {})
    }
}