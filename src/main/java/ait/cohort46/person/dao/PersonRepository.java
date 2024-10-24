package ait.cohort46.person.dao;

import ait.cohort46.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
