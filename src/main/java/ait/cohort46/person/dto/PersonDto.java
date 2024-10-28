package ait.cohort46.person.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;

@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@JsonSubTypes.Type(name = "child", value = ChildDto.class),
		@JsonSubTypes.Type(name = "employee", value = EmployeeDto.class),
		@JsonSubTypes.Type(name = "person", value = PersonDto.class)
})
public class PersonDto {
	private Integer id;
	private String name;
	private LocalDate birthDate;
	private AddressDto address;
}
