package csf.d6.weather.services;

import static csf.d6.weather.Constants.URL_WEATHER;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import csf.d6.weather.WeatherApplication;
import csf.d6.weather.model.Weather;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class WeatherService {
    private final Logger logger = Logger.getLogger(WeatherApplication.class.getName());
    
    public List<Weather> getWeather(String cityname) {
        String API_KEY = System.getenv("OPENWEATHER_API_KEY");
        String url = UriComponentsBuilder
            .fromUriString(URL_WEATHER)
            .queryParam("q", cityname)
            .queryParam("appid", API_KEY) //"de7c6c28aac00ae70193bccadb497054"
            .queryParam("units", "metric")
            .toUriString();

        RequestEntity<Void> req = RequestEntity
            .get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();

        RestTemplate template = new RestTemplate();

        ResponseEntity<String> resp = template.exchange(req, String.class);

        logger.log(Level.INFO, "Status code: " + resp.getStatusCodeValue());
        logger.log(Level.INFO, "Payload: " + resp.getBody());

        try (InputStream is = new ByteArrayInputStream(resp.getBody().getBytes())) {
            final JsonReader reader = Json.createReader(is);
            final JsonObject result = reader.readObject();
            final JsonArray readings = result.getJsonArray("weather");
            final String cityName = result.getString("name");
            final float temperature = (float)result.getJsonObject("main").getJsonNumber("temp").doubleValue();
            return readings.stream()
                .map(v -> (JsonObject)v)
                .map(Weather::create)
                .map(w -> {
                    w.setCityName(cityName);
                    w.setTemperature(temperature);
                    return w;
                })
                .collect(Collectors.toList());

        } catch (Exception ex) { }

        
        return Collections.EMPTY_LIST;
    }    
}
