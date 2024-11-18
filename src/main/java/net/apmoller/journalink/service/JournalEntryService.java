package net.apmoller.journalink.service;

import jakarta.transaction.Transactional;
import net.apmoller.journalink.entity.JournalEntry;
import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.repository.JournalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    public List<JournalEntry> showAllJournalEntries(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(Long id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void saveJournalEntry(JournalEntry journalEntry, String userName){
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        journalEntry.setUser(user);
        JournalEntry savedEntry = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(savedEntry);
        userService.saveNewUser(user);
    }

    @Transactional
    public void deleteById(Long id, String userName){
        User user = userService.findByUserName(userName);
        boolean removed = user.getJournalEntries().removeIf(x -> x.getId() == id);
        if (removed){
            userService.saveNewUser(user);
            journalEntryRepository.deleteById(id);
        }

    }



}
