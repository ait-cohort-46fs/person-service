package ait.cohort46.person.service;

import ait.cohort46.person.dao.PersonRepository;
import ait.cohort46.person.dto.*;
import ait.cohort46.person.dto.exception.PersonNotFoundException;
import ait.cohort46.person.model.Address;
import ait.cohort46.person.model.Child;
import ait.cohort46.person.model.Employee;
import ait.cohort46.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService, CommandLineRunner {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;
    private final PersonModelDtoMapper mapper;

    @Transactional
    @Override
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(mapper.mapToModel(personDto));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return mapper.mapToDto(person);
    }

    @Transactional
    @Override
    public PersonDto removePerson(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        personRepository.delete(person);
        return mapper.mapToDto(person);
    }

    @Transactional
    @Override
    public PersonDto updatePersonName(Integer id, String name) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setName(name);
        return mapper.mapToDto(person);
    }

    @Transactional
    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        person.setAddress(modelMapper.map(addressDto, Address.class));
        return mapper.mapToDto(person);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] findPersonsByCity(String city) {
        return personRepository.findByAddressCityIgnoreCase(city)
                .map(p -> mapper.mapToDto(p))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] findPersonsByName(String name) {
        return personRepository.findByNameIgnoreCase(name)
                .map(p -> mapper.mapToDto(p))
                .toArray(PersonDto[]::new);
    }

    @Transactional(readOnly = true)
    @Override
    public PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge) {
        LocalDate from = LocalDate.now().minusYears(maxAge);
        LocalDate to = LocalDate.now().minusYears(minAge);
        return personRepository.findByBirthDateBetween(from, to)
                .map(p -> mapper.mapToDto(p))
                .toArray(PersonDto[]::new);
    }

    @Override
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return personRepository.getCityPopulation();
    }

    @Override
    public EmployeeDto[] findEmployeesBySalary(int min, int max) {
        Employee[] employees = personRepository.findBySalaryBetween(min, max);
        return modelMapper.map(employees, EmployeeDto[].class);
    }

    @Override
    public ChildDto[] getChildren() {
        Child[] children = personRepository.findChildBy();
        return modelMapper.map(children, ChildDto[].class);
    }

    @Override
    public void run(String... args) throws Exception {
        if (personRepository.count() == 0) {
            Person person = new Person(1000, "John", LocalDate.of(1985, 3, 11),
                    new Address("Tel Aviv", "Ben Gvirol", 81));
            Child child = new Child(2000, "Peter", LocalDate.of(2019, 7, 5),
                    new Address("Ashkelon", "Bar Kohva", 21), "Sun");
            Employee employee = new Employee(3000, "Mary", LocalDate.of(1995, 11, 23),
                    new Address("Rehovot", "Herzl", 7), "Motorola", 20_000);
            personRepository.save(person);
            personRepository.save(child);
            personRepository.save(employee);
        }

    }
}
