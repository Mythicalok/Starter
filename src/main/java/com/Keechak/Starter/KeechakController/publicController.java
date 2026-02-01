package com.Keechak.Starter.KeechakController;

import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.KeechakService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class publicController {

    @Autowired
    private UserService userservice;

    @PostMapping("/create-user")
    public void createuser(@RequestBody User user){
        userservice.savenewuser(user);
    }
}
