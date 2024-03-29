package exercise.service;

import exercise.model.User;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.regex.Pattern;

public final class UserSpecification implements Specification<User> {

    private SearchCriteria searchCriteria;

    public UserSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        // BEGIN
        Expression<String> expressionKey = criteriaBuilder.lower(root.get(searchCriteria.getKey()));
        String pattern = "%" + searchCriteria.getValue().toString().toLowerCase() + "%";
        return criteriaBuilder.like(expressionKey, pattern);
        // END
    }
}
