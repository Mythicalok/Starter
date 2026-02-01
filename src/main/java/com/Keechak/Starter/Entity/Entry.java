package com.Keechak.Starter.Entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@Document(collection = "journal_entries")
public class Entry {
    @Id
    private ObjectId id;

    @NonNull
    String title;

    private String content;

    private LocalDateTime date;

}
