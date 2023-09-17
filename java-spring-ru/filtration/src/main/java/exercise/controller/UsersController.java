package exercise.controller;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.service.SearchCriteria;
import exercise.service.UserSpecification;
import liquibase.util.CollectionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.Expression;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Зависимости для самостоятельной работы
// import org.springframework.data.querydsl.binding.QuerydslPredicate;
// import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserRepository userRepository;

    // BEGIN
    @GetMapping("")
    public List<User> getUsersByName(@RequestParam(required = false) String firstName,
                               @RequestParam(required = false) String lastName) {
        List<Specification> specifications = new ArrayList<>();

        if (firstName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<>("firstName", firstName.toString())));
        }
        if (lastName != null) {
            specifications.add(new UserSpecification(new SearchCriteria<>("lastName", lastName.toString())));
        }

        Specification<User> userSpecification = specifications.stream()
            .reduce(null, (specificationResult, specification) -> {
                if (specificationResult == null) {
                    return specification;
                }
                return specificationResult.and(specification);
            });

        if (!CollectionUtils.isEmpty(specifications)) {
            return this.userRepository.findAll(userSpecification);
        }

        return this.userRepository.findAll();
    }
    // END
}

