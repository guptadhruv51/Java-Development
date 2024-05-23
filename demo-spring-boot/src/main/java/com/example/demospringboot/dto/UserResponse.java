package com.example.demospringboot.dto;

import com.example.demospringboot.models.Gender;
import com.example.demospringboot.models.User;

public class UserResponse
{
    // Must be curated according to what the client needs
    // Act as the response DTO
    private int id;
    private String name;
    private String email;
    private Integer age;
    private Gender gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public static UserResponse from(User user)
    {
        if(user==null)
        {
            return null;
        }
        UserResponse userResponse=new UserResponse();
        userResponse.age=user.getAge();
        userResponse.name=user.getName();
        userResponse.gender=user.getGender();
        userResponse.id=user.getId();
        userResponse.email=user.getEmail();
        return userResponse;
    }
}
