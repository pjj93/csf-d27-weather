package csf.d6.weather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import csf.d6.weather.model.Weather;
import csf.d6.weather.services.WeatherService;
import jakarta.json.Json;
import jakarta.json.JsonObject;

@RestController
public class WeatherRestController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping(path="/api/weather/{city}", produces="application/json")
    public ResponseEntity<String> getCity(@PathVariable(required=true) String city) {

        List<Weather> listWeather = weatherSvc.getWeather(city);
        Weather weather = listWeather.get(0);
        JsonObject weatherJObj = Json.createObjectBuilder()
            .add("city", weather.getCityName())
            .add("description", weather.getDescription())
            .add("temp", weather.getTemperature())
            .build();

        return ResponseEntity.ok().body(weatherJObj.toString());
    }
}
