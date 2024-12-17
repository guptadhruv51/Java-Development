package com.example.security.section1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController
{
    @GetMapping("/notices")
    public String getNotices()
    {
        return "Here are the notice details from DB";
    }

}