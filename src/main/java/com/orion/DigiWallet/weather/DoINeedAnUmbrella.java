package com.orion.DigiWallet.weather;

//TIGHT COUPLING WITH GOOGLE WEATHER API
public class DoINeedAnUmbrella {

    private  WeatherApi weatherApi;

    public DoINeedAnUmbrella(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public boolean checkIfNeedUmbrella(String location) {
        String weatherCondition = weatherApi.getWeatherData(location);
        return "RAINY".equalsIgnoreCase(weatherCondition);
    }
}
