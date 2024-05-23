package com.example.demospringboot.dto;

import com.example.demospringboot.models.Gender;
import com.example.demospringboot.models.User;

public class UserUpdateRequest
{
    private Integer id;
    private String name;
    private String email;
    private String password;
    private Gender gender;
    private Integer age;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public User to(Integer userId){
        User user = new User();
        user.setAge(this.age);
        user.setName(this.name);
        user.setEmail(this.email);
        user.setPassword(this.password);
        user.setGender(this.gender);
        user.setId(userId);


        return user;
    }
}
