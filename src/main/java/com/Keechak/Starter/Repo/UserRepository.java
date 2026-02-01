package com.Keechak.Starter.Repo;

import com.Keechak.Starter.Entity.Entry;
import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.KeechakService.UserService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User,ObjectId> {
    User findByUsername(String UserName);
    void deleteByUsername (String username);
}
