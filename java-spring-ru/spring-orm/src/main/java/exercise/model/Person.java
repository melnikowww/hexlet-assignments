package exercise.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// BEGIN
@Entity
@Data
// END
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // BEGIN
    private String firstName;
    private String lastName;
    // END
}
