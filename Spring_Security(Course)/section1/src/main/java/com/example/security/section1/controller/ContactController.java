package com.example.security.section1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController
{
    @GetMapping("/contact")
    public String saveContactenquiryDetails()
    {
        return "Enquiry details have been saved to DB";
    }
}
