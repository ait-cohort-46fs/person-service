package ait.cohort46.person.dao;

import ait.cohort46.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    @Query("select p from Citizen p where p.name=?1")
    Stream<Person> findByNameIgnoreCase(String name);

    @Query("select p from Citizen p where p.address.city=:cityName")
    Stream<Person> findByAddressCityIgnoreCase(@Param("cityName") String city);

    Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

    @Query("select p.address.city, count(p) AS population from Citizen p group by p.address.city order by count(p) desc ")
    List<Object[]> getCityPopulation();
}
