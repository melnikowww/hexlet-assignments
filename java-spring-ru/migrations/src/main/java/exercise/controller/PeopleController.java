package exercise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {


    private final JdbcTemplate jdbc;


    @RequestMapping(
        path = "",
        method = RequestMethod.POST)
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
//    @RequestMapping(
//        method = RequestMethod.GET,
//        path = "people/"
//    )
////    @GetMapping("people/")
//    public String getPeople() throws JsonProcessingException {
//        String query = "SELECT * FROM person";
//        List<Map<String, Object>> allPeople = jdbc.queryForList(query);
//        return "";
//    }
    // END
}
