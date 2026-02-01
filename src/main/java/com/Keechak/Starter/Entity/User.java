package com.Keechak.Starter.Entity;

import lombok.*;
import org.apache.el.stream.Stream;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "Users")
public class User {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
   @NonNull
   private String username;
   @NonNull
    private String password;
   @DBRef
   List<Entry> journalentries = new ArrayList<>();
   private List<String> Roles;

}
