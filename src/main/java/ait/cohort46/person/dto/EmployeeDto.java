package ait.cohort46.person.dto;

import lombok.Getter;

@Getter
public class EmployeeDto extends PersonDto{
    private String company;
    private Integer salary;
}
