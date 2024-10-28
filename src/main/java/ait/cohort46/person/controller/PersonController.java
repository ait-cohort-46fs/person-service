package ait.cohort46.person.controller;

import ait.cohort46.person.dto.*;
import ait.cohort46.person.service.PersonService;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping
    public Boolean addPerson(@RequestBody PersonDto personDto) {
        return personService.addPerson(personDto);
    }

    @GetMapping("/{id}")
    public PersonDto findPersonById(@PathVariable Integer id) {
        return personService.findPersonById(id);
    }

    @DeleteMapping("/{id}")
    public PersonDto removePerson(@PathVariable Integer id) {
        return personService.removePerson(id);
    }

    @PatchMapping("/{id}/name/{name}")
    public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
        return personService.updatePersonName(id, name);
    }

    @PatchMapping("/{id}/address")
    public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto) {
        return personService.updatePersonAddress(id, addressDto);
    }

    @GetMapping("/city/{city}")
    public PersonDto[] findPersonsByCity(@PathVariable String city) {
        return personService.findPersonsByCity(city);
    }

    @GetMapping("/name/{name}")
    public PersonDto[] findPersonsByName(@PathVariable String name) {
        return personService.findPersonsByName(name);
    }

    @GetMapping("/ages/{min}/{max}")
    public PersonDto[] findPersonsBetweenAge(@PathVariable Integer min, @PathVariable Integer max) {
        return personService.findPersonsBetweenAge(min, max);
    }

    @GetMapping("/population/city")
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return personService.getCitiesPopulation();
    }

    @GetMapping("/salary/{min}/{max}")
    public EmployeeDto[] findEmployeesBySalary(@PathVariable Integer min, @PathVariable Integer max) {
        return personService.findEmployeesBySalary(min, max);
    }

    @GetMapping("/children")
    public ChildDto[] findAllChildren() {
        return personService.getChildren();
    }
}
