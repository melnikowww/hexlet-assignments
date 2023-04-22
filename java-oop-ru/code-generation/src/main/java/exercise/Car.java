package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }
    @SneakyThrows
    public static Car unserialize(String jsonCar) {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = objectMapper.readValue(jsonCar, Car.class);
        return car;
    }
    // END
}
