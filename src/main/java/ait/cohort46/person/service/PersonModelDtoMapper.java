package ait.cohort46.person.service;

import ait.cohort46.person.dto.PersonDto;
import ait.cohort46.person.dto.exception.UnknownPersonTypeException;
import ait.cohort46.person.model.Person;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonModelDtoMapper {
    private static final String MODEL_PACKAGE = "ait.cohort46.person.model.";
    private static final String DTO_SUFFIX = "Dto";
    private static final String DTO_PACKAGE = "ait.cohort46.person.dto.";
    private final ModelMapper modelMapper;

    public PersonDto mapToDto(Person person) {
        String dtoClassName = person.getClass().getSimpleName() + DTO_SUFFIX;
        try {
            @SuppressWarnings("unchecked")
            Class<? extends PersonDto> clazz = (Class<? extends PersonDto>) Class.forName(DTO_PACKAGE + dtoClassName);
            return modelMapper.map(person, clazz);
        } catch (ClassNotFoundException e) {
            throw new UnknownPersonTypeException();
        }
    }

    public Person mapToModel(PersonDto personDto) {
        String modelClassName = personDto.getClass().getSimpleName();
        modelClassName = modelClassName.substring(0, modelClassName.length() - 3);
        try {
            @SuppressWarnings("unchecked")
            Class<? extends Person> clazz = (Class<? extends Person>) Class.forName(MODEL_PACKAGE + modelClassName);
            return modelMapper.map(personDto, clazz);
        } catch (ClassNotFoundException e) {
            throw new UnknownPersonTypeException();
        }
    }
}
