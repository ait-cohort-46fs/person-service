package ait.cohort46.person.dao;

import ait.cohort46.person.dto.CityPopulationDto;
import ait.cohort46.person.model.Child;
import ait.cohort46.person.model.Employee;
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

    @Query("select new ait.cohort46.person.dto.CityPopulationDto(p.address.city, count(p)) from Citizen p  group by p.address.city order by count(p) desc")
    List<CityPopulationDto> getCityPopulation();

    @Query("select c from Child c")
    Child[] findChildBy();

    @Query("select e from Employee e where e.salary between ?1 and ?2")
    Employee[] findBySalaryBetween(double from, double to);
}
