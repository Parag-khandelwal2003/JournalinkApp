package net.apmoller.journalink.controller;


import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private UserService userService;
    @GetMapping
    public String greeting(){
        return "Welcome to Admin Dashboard!";
    }

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){

        List<User> users = userService.showAllUsers();

        if (users != null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create-admin")
    public ResponseEntity<?> createAdminUser(@RequestBody User user){
        userService.saveAdminUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
