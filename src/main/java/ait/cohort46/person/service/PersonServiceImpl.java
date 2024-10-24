package ait.cohort46.person.service;

import ait.cohort46.person.dao.PersonRepository;
import ait.cohort46.person.dto.AddressDto;
import ait.cohort46.person.dto.CityPopulationDto;
import ait.cohort46.person.dto.PersonDto;
import ait.cohort46.person.dto.exception.PersonNotFoundException;
import ait.cohort46.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public Boolean addPerson(PersonDto personDto) {
        if (personRepository.existsById(personDto.getId())) {
            return false;
        }
        personRepository.save(modelMapper.map(personDto, Person.class));
        return true;
    }

    @Override
    public PersonDto findPersonById(Integer id) {
        Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
        return modelMapper.map(person, PersonDto.class);
    }

    @Override
    public PersonDto removePerson(Integer id) {
        return null;
    }

    @Override
    public PersonDto updatePersonName(Integer id, String name) {
        return null;
    }

    @Override
    public PersonDto updatePersonAddress(Integer id, AddressDto addressDto) {
        return null;
    }

    @Override
    public PersonDto[] findPersonsByCity(String city) {
        return new PersonDto[0];
    }

    @Override
    public PersonDto[] findPersonsByName(String name) {
        return new PersonDto[0];
    }

    @Override
    public PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge) {
        return new PersonDto[0];
    }

    @Override
    public Iterable<CityPopulationDto> getCitiesPopulation() {
        return null;
    }
}
