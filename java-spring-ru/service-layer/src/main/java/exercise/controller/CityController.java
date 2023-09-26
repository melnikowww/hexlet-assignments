package exercise.controller;
import com.fasterxml.jackson.core.JsonProcessingException;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(
        path = "/cities/{id}",
        produces = "application/json")
    public String getCityWeatherInfo(@PathVariable(name = "id") Long id) {
        City city = cityRepository.findById(id).orElseThrow();
        String weatherInfo = weatherService.getWeatherInfo(city);
        return weatherInfo;
    }

    @GetMapping(
        path = "/search",
        produces = "application/json"
    )
    public String getCity(@RequestParam(required = false, name = "name") String name) throws JsonProcessingException {
        StringBuilder stringBuilder = new StringBuilder("[");
        boolean first = true;

        List<City> cities = name != null ? cityRepository.findByNameStartingWithIgnoreCase(name) :
            cityRepository.findAllByOrderByName() ;

        for (City city: cities) {
            if (!first) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(weatherService.getTemperature(city));
            first = false;
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
    // END
}

