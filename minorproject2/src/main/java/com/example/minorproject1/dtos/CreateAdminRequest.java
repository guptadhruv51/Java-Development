package com.example.minorproject1.dtos;

import com.example.minorproject1.models.Admin;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateAdminRequest
{
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String name;


    public Admin to()
    //dto to entity
    {
        return Admin.builder()
                .name(this.name)
                .user(
                        User.builder()
                                .username(this.username)
                                .password(this.password)
                                .build()
                )
                .build();
    }

}
