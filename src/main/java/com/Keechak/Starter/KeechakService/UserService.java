package com.Keechak.Starter.KeechakService;

import com.Keechak.Starter.Entity.Entry;
import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.Repo.JournalEntryRepository;
import com.Keechak.Starter.Repo.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.sql.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static final PasswordEncoder passwordencoder= new BCryptPasswordEncoder();



    public void saveuser(User user){
        user.setPassword(passwordencoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }

    public List<User> getAll(User user){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUsername(userName);
    }
}
//controller -------->    service ----->   repository ----->