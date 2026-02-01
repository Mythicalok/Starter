package com.Keechak.Starter.KeechakController;

import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.KeechakService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userservice;
    private User user;

    @GetMapping("/all-users")
    public ResponseEntity<?> getallusers(){
        List<User> all = userservice.getAll(user);
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-new-Admin")
    public void createUser(@RequestBody User user){
        userservice.saveAdmin(user);
    }
}
