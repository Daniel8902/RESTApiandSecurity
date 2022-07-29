package com.security.week4cz2.Controller1;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@PreAuthorize("hasAnyRole('admin')")

public class UserController {
    //@PreAuthorize("hasAnyAuthority('USER_READ', 'ADMIN')")
    @GetMapping("/getuser")
    public String getUser () {


        return "This is user!";


    }
  //  @PreAuthorize("hasAnyRole('user', 'admin')")
    //@PreAuthorize("hasAnyAuthority('USER_EDIT', 'ADMIN')")

    @GetMapping("/update")
    public String updateUser () {


        return "User updated!";


    }
}
