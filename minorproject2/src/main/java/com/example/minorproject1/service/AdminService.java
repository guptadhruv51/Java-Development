package com.example.minorproject1.service;

import com.example.minorproject1.dtos.CreateAdminRequest;
import com.example.minorproject1.dtos.CreateStudentRequest;
import com.example.minorproject1.models.Admin;
import com.example.minorproject1.models.Authority;
import com.example.minorproject1.models.Student;
import com.example.minorproject1.models.User;
import com.example.minorproject1.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
    @Autowired
    UserService userService;
    @Autowired
    AdminRepository adminRepository;
    public Integer create(CreateAdminRequest createAdminRequest)
    {

        Admin admin=createAdminRequest.to();
        User user=this.userService.createUser(admin.getUser(), Authority.admin);
        admin.setUser(user);
        this.adminRepository.save(admin);
        return admin.getId();
    }

}
