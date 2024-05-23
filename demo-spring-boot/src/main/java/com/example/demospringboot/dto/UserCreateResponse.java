package com.example.demospringboot.dto;

import com.example.demospringboot.models.User;

public class UserCreateResponse
{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public static UserCreateResponse from(User user)
    {
        UserCreateResponse userCreateResponse=new UserCreateResponse();
        userCreateResponse.setId(user.getId());
        return userCreateResponse;

    }
}
