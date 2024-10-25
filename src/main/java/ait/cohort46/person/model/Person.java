package ait.cohort46.person.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
@Entity(name = "Citizen")
@Table(name = "persons")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person implements Serializable {
    @Id
    private int id;
    @Setter
    private String name;
    private LocalDate birthDate;
    @Setter
    private Address address;
}
