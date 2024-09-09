package com.hzbank.aaronkotlin

data class WeatherBean(var code: String = "",
                       var msg: String = "",
                       var province: String = "",
                       var city: String = "",
                       var temperature: String = "",
                       var weather: String = "",
                       var wind_direction: String = "",
                       var wind_power: String = "",
                       var humidity: String = "",
                       var reporttime: String = "")
