package com.Keechak.Starter.KeechakService;

import com.Keechak.Starter.Entity.Entry;
import com.Keechak.Starter.Entity.User;
import com.Keechak.Starter.Repo.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public void saveEntry(Entry myentry, String userName){
        try{
            User user = userService.findByUserName(userName);
            myentry.setDate(LocalDateTime.now());
            Entry saved = journalEntryRepository.save(myentry);
            user.getJournalentries().add(saved);
            userService.saveuser(user);
        }
        catch(Exception e){
            System.out.print(e);
            throw new RuntimeException("something error has occured: " + e);
        }
    }
    public void saveEntry(Entry myentry){
        journalEntryRepository.save(myentry);
    }

    public List<Entry> getAll(Entry myentry){
        return journalEntryRepository.findAll();
    }

    public Optional<Entry> findbyId(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean remove = false;
        try{
            User user = userService.findByUserName(userName);
            remove = user.getJournalentries().removeIf(x -> x.getId().equals(id));
            if (remove) {
                userService.saveuser(user);
                journalEntryRepository.deleteById(id);
            }
        }catch(Exception e){
            System.out.print(e);
            throw new RuntimeException("an error occured while deleting the entry!!" + e);
        }
        return remove;
    }
}
//controller -------->    service ----->   repository ----->