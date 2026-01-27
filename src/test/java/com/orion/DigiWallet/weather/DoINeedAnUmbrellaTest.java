package com.orion.DigiWallet.weather;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoINeedAnUmbrellaTest {

    @MockitoBean
    private WeatherApi weatherApi;

    @Autowired
    private DoINeedAnUmbrella doINeedAnUmbrella;



        @Test
        void givenRainyWeather_whenCheckIfNeedUmbrella_thenReturnTrue() {
        // Arrange
        String location = "New York";
        when(weatherApi.getWeatherData(location)).thenReturn("RAINY");
        // Act
        boolean result = doINeedAnUmbrella.checkIfNeedUmbrella(location);
        // Assert
        assertTrue(result);
        }

}