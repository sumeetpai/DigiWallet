package com.orion.DigiWallet.weather;

public class GoogleWeatherApi {

    private String apiKey;
    private String apiUrl;

    //connect to Google Weather API and return weather data
    String getWeatherData(String location) {
        // Dummy implementation for illustration using APIKey and URL
        //array ["SUNNY," "RAINY", "CLOUDY", "WINDY"]
        String[] weatherConditions = {"SUNNY", "RAINY", "CLOUDY", "WINDY"};
        int index = (int) (Math.random() * weatherConditions.length);
        return weatherConditions[index];
    }
}
