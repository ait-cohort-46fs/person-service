package ait.cohort46.person.dao;

import ait.cohort46.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByNameIgnoreCase(String name);

    List<Person> findByAddressCityIgnoreCase(String city);

    List<Person> findByBirthDateBetween(LocalDate from, LocalDate to);
}
