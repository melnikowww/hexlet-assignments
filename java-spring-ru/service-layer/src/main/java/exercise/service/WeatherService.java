package exercise.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import exercise.HttpClient;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import exercise.CityNotFoundException;
import exercise.repository.CityRepository;
import exercise.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ResponseBody;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public String getWeatherInfo(City city) {
        return client.get("http://weather/api/v2/cities/" + city.getName());
    }

    public String getTemperature(City city) throws JsonProcessingException {
        String weather = client.get("http://weather/api/v2/cities/" + city.getName())
            .replace("{", "")
            .replace("}", "");

        String temp = Arrays.stream(weather.split(","))
            .filter(pair -> pair.contains("temperature") || pair.contains("name"))
            .collect(Collectors.joining(", "));

        return "{" + temp + "}";
    }
    // END
}
