package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.model.Course;
import exercise.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    @GetMapping(path = "")
    public Iterable<Course> getCourses() {
        return courseRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Course getCourse(@PathVariable long id) {
        return courseRepository.findById(id);
    }

    // BEGIN
    @GetMapping(
        path = "/{id}/previous",
        produces = "application/json"
    )
    public String getPrev(@PathVariable long id) throws JsonProcessingException {
        Course course = courseRepository.findById(id);
        List<Long> path;
        if (course.getPath() != null) {
             path = Arrays.stream(course.getPath()
                .split("\\."))
                 .map(x -> Long.parseLong(x))
                .toList();
        } else {
            return "[]";
        }

        List<Course> list = new ArrayList<>();
        list.addAll((Collection<? extends Course>) getCourses());

        list = list.stream()
            .filter(x -> path.contains(x.getId()))
            .toList();

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> resultList = new ArrayList<>();
        for (Course obj: list) {
            resultList.add(objectMapper.writeValueAsString(obj));
        }

        return String.join(",", resultList);
    }
    // END

}
