package com.example.demospringboot.dto;

import com.example.demospringboot.models.User;

public class UserUpdateResponse
{
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public static UserUpdateResponse from(User user)
    {
        UserUpdateResponse userUpdateResponse=new UserUpdateResponse();
        if(user==null)
        {
            return null;
        }
        userUpdateResponse.setId(user.getId());
        return userUpdateResponse;

    }
}
