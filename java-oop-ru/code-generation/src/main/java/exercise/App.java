package exercise;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    @SneakyThrows
    public static void save(Path path, Car car) {
        Files.writeString(path, car.serialize());
    }
    @SneakyThrows
    public static Car extract(Path path) {
        return Car.unserialize(Files.readString(path));
    }
}
// END
