package com.Keechak.Starter.Repo;

import com.Keechak.Starter.Entity.Entry;
import com.Keechak.Starter.KeechakService.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public abstract interface JournalEntryRepository extends MongoRepository<Entry,ObjectId>{

}
