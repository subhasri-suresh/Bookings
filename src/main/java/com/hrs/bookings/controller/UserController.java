package com.hrs.bookings.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/")
public class UserController {

    /**
     * Test Method
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }
}
