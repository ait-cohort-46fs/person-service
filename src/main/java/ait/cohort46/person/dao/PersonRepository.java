package ait.cohort46.person.dao;

import ait.cohort46.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Stream<Person> findByNameIgnoreCase(String name);

    Stream<Person> findByAddressCityIgnoreCase(String city);

    Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
}
