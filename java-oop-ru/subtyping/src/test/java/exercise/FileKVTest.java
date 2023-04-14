package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import java.util.Map;
import exercise.FileKV;
import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void constructorTest() {
        KeyValueStorage data = new FileKV("src/test/resources/file", Map.of("key", "value"));
        assertThat(data.get("key", "default")).isEqualTo("value");
        assertThat(data.get("random", "default")).isEqualTo("default");
        assertThat(data.toMap()).isEqualTo(Map.of("key", "value"));
        data.set("key", "happyNewYear");
        assertThat(data.get("key", "default")).isEqualTo("happyNewYear");
        data.unset("key");
        assertThat(data.get("key", "default")).isEqualTo("default");
    }

    // END
}
