package com.Keechak.Starter.KeechakController;
import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.KeechakService.UserService;
import com.Keechak.Starter.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getallusers(@RequestBody User user){
        return userservice.getAll(user);
    }


    @PutMapping
    public ResponseEntity<?> updateuser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userIndb = userservice.findByUserName(userName);
        userIndb.setUsername(user.getUsername());
        userIndb.setPassword(user.getPassword());
        userservice.savenewuser(userIndb);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteuserbyId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
