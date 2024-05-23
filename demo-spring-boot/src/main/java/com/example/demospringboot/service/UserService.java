package com.example.demospringboot.service;

import com.example.demospringboot.dto.UserCreateRequest;
import com.example.demospringboot.dto.UserUpdateRequest;
import com.example.demospringboot.models.User;
import com.example.demospringboot.repository.UserRepo;

public class UserService
{
    private UserRepo userRepo;

    public UserService() {
        this.userRepo = new UserRepo();
    }

    public User create(UserCreateRequest userCreateRequest)
    {
        //accept dto and convert the DTO into model and then pass model to repo layer
        User user= userCreateRequest.to();
        user=userRepo.create(user);
        return user;
    }

    public User get(Integer userId)
    {
        return userRepo.get(userId);

    }
    /*public User to(UserCreateRequest userrequest)
    {
        User user=new User();
        user.setGender(userrequest.getGender());
        user.setPassword(userrequest.getPassword());
        user.setAge(userrequest.getAge());
        user.setName(userrequest.getName());
        user.setEmail(userrequest.getEmail());
        return user;
    }*/

    public User update(Integer id, UserUpdateRequest userUpdateRequest)
    {
        User user=userUpdateRequest.to(id);
        return this.userRepo.update(id,user);
    }


    public void delete(Integer id)
    {
        this.userRepo.delete(id);
    }
}
