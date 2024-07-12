package com.example.minorproject1.Controllers;

import com.example.minorproject1.dtos.CreateAdminRequest;
import com.example.minorproject1.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/admin")
public class AdminController
{
    @Autowired
    AdminService adminService;
    @PostMapping("/admin")
    public Integer createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest)
    {
            return adminService.create(createAdminRequest);
    }
}
