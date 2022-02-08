package csf.d6.weather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import csf.d6.weather.model.Weather;
import csf.d6.weather.services.WeatherService;

@RestController
@CrossOrigin("*")
public class WeatherRestController {

    @Autowired
    private WeatherService weatherSvc;

    @GetMapping(path="/api/weather/{city}", produces="application/json")
    public ResponseEntity<String> getCity(@PathVariable(required=true) String city) {

        Weather weather = weatherSvc.getWeather(city);
        return ResponseEntity.ok(weather.toJson().toString());
    }
}
