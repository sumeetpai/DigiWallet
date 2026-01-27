package com.orion.DigiWallet.weather;

public class StubWeatherApi implements WeatherApi{

    @Override
    public String getWeatherData(String location) {
        // Stub implementation for testing purposes
        return "RAINY";
    }
}
