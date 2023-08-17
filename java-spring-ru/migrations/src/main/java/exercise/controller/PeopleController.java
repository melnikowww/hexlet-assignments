package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @PostMapping
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(
        produces = "application/json"
    )
    public String getPeople() throws JsonProcessingException {
        String query = "SELECT * FROM person";
        List<Map<String, Object>> allPeople = jdbc.queryForList(query);
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.writeValueAsString(allPeople);
    }

    @GetMapping(
        path = "/{id}",
        produces = "application/json"
    )
    public String getPerson(@PathVariable int id) throws JsonProcessingException {
        String query = "SELECT * FROM PERSON WHERE id = (?)";
        Map<String, Object> person = jdbc.queryForMap(query, id);
        JsonMapper jsonMapper = new JsonMapper();
        return jsonMapper.writeValueAsString(person);
    }
    // END
}
