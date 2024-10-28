package ait.cohort46.person.service;

import ait.cohort46.person.dto.*;

public interface PersonService {
	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);
	
	PersonDto removePerson(Integer id);
	
	PersonDto updatePersonName(Integer id, String name);
	
	PersonDto updatePersonAddress(Integer id, AddressDto addressDto);
	
	PersonDto[] findPersonsByCity(String city);
	
	PersonDto[] findPersonsByName(String name);
	
	PersonDto[] findPersonsBetweenAge(Integer minAge, Integer maxAge);
	
	Iterable<CityPopulationDto> getCitiesPopulation();

	EmployeeDto[] findEmployeesBySalary(int min, int max);

	ChildDto[] getChildren();
}
