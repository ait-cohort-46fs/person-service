package ait.cohort46.person.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class PersonDto {
	private Integer id;
	private String name;
	private LocalDate birthDate;
	private AddressDto address;
}
