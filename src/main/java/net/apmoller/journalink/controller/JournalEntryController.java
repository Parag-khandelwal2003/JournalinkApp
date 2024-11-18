package net.apmoller.journalink.controller;

import net.apmoller.journalink.entity.JournalEntry;
import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.service.JournalEntryService;
import net.apmoller.journalink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;



    @GetMapping // path => /journal (By default)
    public String entry(){
        return "<h1> Welcome User! </h1> <br> Go to <i> <strong> /all-entries </strong> </i> for all entries";
    }

    @GetMapping("all-entries")
    public ResponseEntity<?> getAllJournalEntriesOfUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        List<JournalEntry> journalEntries = user.getJournalEntries();
        if(journalEntries != null && !journalEntries.isEmpty()){
            return new ResponseEntity<>(journalEntries, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getEntryById(@PathVariable Long myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);

        Optional<JournalEntry> entry = user.getJournalEntries().stream().filter(x -> x.getId() == myId).findFirst();
        if (entry.isPresent()){
            return new ResponseEntity<>(entry, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping // by default path => /journal
    public ResponseEntity<?> createEntry(@RequestBody JournalEntry entry){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = userService.findByUserName(userName);
        journalEntryService.saveJournalEntry(entry, userName);
        return new ResponseEntity<>(entry, HttpStatus.CREATED);

    }
//
    @DeleteMapping("deleteId/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        journalEntryService.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//
//    @PutMapping("updateId/{id}")
//    public JournalEntry updateEntryById(@PathVariable Long id, @RequestBody JournalEntry entry){
//        return journalEntries.put(id, entry);
//    }

}
