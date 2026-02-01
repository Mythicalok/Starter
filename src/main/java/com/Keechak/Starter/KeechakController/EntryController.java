package com.Keechak.Starter.KeechakController;

import com.Keechak.Starter.Entity.Entry;
import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.KeechakService.JournalEntryService;
import com.Keechak.Starter.KeechakService.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class EntryController {

    @Autowired
    private JournalEntryService journalEntryService;
    private Entry myentry;
    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllentriesofuser(@PathVariable String userName){
        User user = userService.findByUserName(userName);
        List<Entry> all = user.getJournalentries();
        if(all!= null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping({"{userName}"})//https://localhost:8080/journal
    public ResponseEntity<Entry> createEntry(@RequestBody Entry myentry, @PathVariable String userName){
        try{
            journalEntryService.saveEntry(myentry,userName);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myid}")
    public ResponseEntity<Entry> getJournalEntryById(@PathVariable ObjectId myid){
        Optional<Entry> entry = journalEntryService.findbyId(myid);
        if(entry.isPresent()){
            return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid,@PathVariable String userName){
        journalEntryService.deleteById(myid,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> UpdateJournalById(@PathVariable ObjectId myid,
                                               @RequestBody Entry newupdatedEntry,
    @PathVariable String userName){
        Entry old = journalEntryService.findbyId(myid).orElse(null);
        if(old != null){
            old.setTitle(newupdatedEntry.getTitle() != null && !newupdatedEntry.getTitle().equals("") ? newupdatedEntry.getTitle() : old.getTitle());
            old.setContent(newupdatedEntry.getContent() != null && !newupdatedEntry.getContent().equals("") ? newupdatedEntry.getContent() : old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
