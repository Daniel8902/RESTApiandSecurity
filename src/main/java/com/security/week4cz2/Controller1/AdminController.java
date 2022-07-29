package com.security.week4cz2.Controller1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('admin')")

public class AdminController {

   // @PreAuthorize("hasAnyRole('user', 'admin')")

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getadmin")
    public String getAdmin  () {


        return "This is admin!";


    }
    //@PreAuthorize("hasRole('admin')")

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createuser")
    public String createUser  () {


        return "User created";


    }

    @PreAuthorize("hasAuthority('ADMIN')")
    //@PreAuthorize("hasRole('admin')")
    @GetMapping("/delet")
    public String deleteUser   () {


        return "User deleted!";


    }
    @GetMapping("/hello")
    public String first() {


        return "hello world";


    }
}
