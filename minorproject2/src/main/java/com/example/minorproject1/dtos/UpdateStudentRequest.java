package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Student;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateStudentRequest
{
    private String name;

    private String email;
    @NotBlank
    private String mobile;
    public Student to()
    //dto to entity
    {
        return Student.builder()
                .name(this.name)
                .email(this.email)
                .mobile(this.mobile)
                .build();
    }
}