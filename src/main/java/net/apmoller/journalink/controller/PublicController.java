package net.apmoller.journalink.controller;

import net.apmoller.journalink.entity.User;
import net.apmoller.journalink.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Status : OK!";
    }

    @GetMapping("journal-check")
    public String check(){
        return "WORKING!!";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        userService.saveUser(user);
    }
}
