package com.example.demo_redis.dto;

import com.example.demo_redis.model.Person;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class requestPerson
{
    private String name;
    private Integer age;
    private Double creditScore;
    private Boolean isSeniorCitizen;


    public Person to()
{
    return Person.builder().name(this.name)
            .id(UUID.randomUUID().toString())
            .age(this.age)
            .creditScore(this.creditScore)
            .isSeniorCitizen(this.isSeniorCitizen)
            .build();
    }
}
