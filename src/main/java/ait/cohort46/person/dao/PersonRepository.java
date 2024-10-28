package ait.cohort46.person.dao;

import ait.cohort46.person.dto.CityPopulationDto;
import ait.cohort46.person.model.Child;
import ait.cohort46.person.model.Employee;
import ait.cohort46.person.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    Stream<Person> findByNameIgnoreCase(String name);

    Stream<Person> findByAddressCityIgnoreCase(String city);

    Stream<Person> findByBirthDateBetween(LocalDate from, LocalDate to);

    @Query("select new ait.cohort46.person.dto.CityPopulationDto(p.address.city, count(p)) from Citizen p  group by p.address.city order by count(p) desc")
    List<CityPopulationDto> getCityPopulation();

    Child[] findChildBy();

    Employee[] findBySalaryBetween(double from, double to);
}
