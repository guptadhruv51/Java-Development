package com.example.demospringboot.repository;

import com.example.demospringboot.exceptions.NotFoundException;
import com.example.demospringboot.models.User;

import java.util.HashMap;
import java.util.Random;

public class UserRepo
{
    HashMap<Integer,User> userMap=new HashMap<>();

    public User create (User user)
    {
        Integer userId=new Random().nextInt(100);
        user.setId(userId);
    this.userMap.put(userId,user);
    return user;
    }
    public User get(Integer userId)
    {
        return this.userMap.get(userId);
    }

    public User update(Integer id, User user)
    {
        if(!this.userMap.containsKey(id))
        {
            return null;
        }
        this.userMap.put(id,user);
        return user;
    }

    public void delete(Integer id) throws NotFoundException{
        if(!userMap.containsKey(id))
        {
            throw new NotFoundException("User not Found");
        }
        this.userMap.remove(id);
    }
}
