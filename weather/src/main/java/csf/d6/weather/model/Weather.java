package csf.d6.weather.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Weather {
    private String cityName;
    private String main;
    private String description;
    private String icon;
    private Float temperature;

    public String getCityName() { return this.cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public String getMain() { return main; }
    public void setMain(String main) { this.main = main; }

    public String getDescription() { return this.description; }
    public void setDescription(String description) { this.description = description; }

    public String getIcon() { return this.icon; }
    public void setIcon(String icon) { 
        this.icon = icon;
    }

    public Float getTemperature() { return this.temperature; }
    public void setTemperature(Float temperature) { this.temperature = temperature; }

    public static Weather create(JsonObject o) {
        final Weather w = new Weather();
        w.setMain(o.getString("main"));
        w.setDescription(o.getString("description"));
        w.setIcon(o.getString("icon"));
        return w;
    }

    public static Weather toWeather(String s) {
		try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
			JsonReader reader = Json.createReader(is);
			return toWeather(reader.readObject());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static Weather toWeather(JsonObject o) {
		Weather w = new Weather();
		w.setCityName(o.getString("name"));
		JsonArray arr = o.getJsonArray("weather");
		if (!arr.isEmpty()) {
			JsonObject wo = arr.getJsonObject(0);
			w.setMain(wo.getString("main"));
			w.setDescription(wo.getString("description"));
			w.setIcon(wo.getString("icon"));
		}
		w.setTemperature((float)o.getJsonObject("main").getJsonNumber("temp").doubleValue());
		return (w);
	}
    
    public JsonObject toJson() {
        return Json.createObjectBuilder()
            .add("cityName", cityName)
            .add("main", main)
            .add("description", description)
            .add("icon", icon)
            .add("temperature", temperature)
            .build();
    }    
}
